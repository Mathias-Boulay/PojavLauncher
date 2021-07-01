package net.kdt.pojavlaunch.customcontrols.handleview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import net.kdt.pojavlaunch.AndroidLWJGLKeycode;
import net.kdt.pojavlaunch.R;
import net.kdt.pojavlaunch.customcontrols.buttons.ControlButton;
import net.kdt.pojavlaunch.customcontrols.ControlData;

import static net.kdt.pojavlaunch.customcontrols.handleview.ActionPopupWindow.setPercentageText;

public class EditControlButtonPopup {

    protected Dialog dialog;
    protected View v;
    protected AlertDialog.Builder builder;

    protected EditText editName;
    protected Spinner[] spinnersKeycode;

    protected CheckBox checkToggle;
    protected CheckBox checkPassThrough;
    protected CheckBox checkDynamicPosition;
    protected CheckBox checkHoldAlt;
    protected CheckBox checkHoldCtrl;
    protected CheckBox checkHoldShift;

    protected EditText editWidth;
    protected EditText editHeight;
    protected EditText editDynamicX;
    protected EditText editDynamicY;

    protected SeekBar seekBarOpacity;
    protected SeekBar seekBarCornerRadius;
    protected SeekBar seekBarStrokeWidth;

    protected ImageButton buttonBackgroundColor;
    protected ImageButton buttonStrokeColor;

    protected TextView textOpacity;
    protected TextView textCornerRadius;
    protected TextView textStrokeWidth;

    protected final ControlButton button;
    protected final ControlData properties;

    protected ArrayAdapter<String> adapter;
    protected String[] specialArr;


    public EditControlButtonPopup(ControlButton button){
        this.button = button;
        this.properties = button.getProperties();

        initializeEditDialog(button.getContext());

        //Create the finalized dialog
        dialog = builder.create();
        dialog.setOnShowListener(dialogInterface -> setEditDialogValues());


        dialog.show();
    }

    protected void initializeEditDialog(Context ctx){
        //Create the editing dialog
        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = layoutInflater.inflate(R.layout.control_button_setting,null);

        builder = new AlertDialog.Builder(ctx);
        builder.setTitle(ctx.getResources().getString(R.string.customctrl_edit, properties.name));
        builder.setView(v);

        //Linking a lot of stuff
        editName = v.findViewById(R.id.editName_editText);

        spinnersKeycode = new Spinner[]{
                v.findViewById(R.id.editMapping_spinner_1),
                v.findViewById(R.id.editMapping_spinner_2),
                v.findViewById(R.id.editMapping_spinner_3),
                v.findViewById(R.id.editMapping_spinner_4)
        };

        checkToggle = v.findViewById(R.id.checkboxToggle);
        checkPassThrough = v.findViewById(R.id.checkboxPassThrough);

        editWidth = v.findViewById(R.id.editSize_editTextX);
        editHeight = v.findViewById(R.id.editSize_editTextY);

        editDynamicX = v.findViewById(R.id.editDynamicPositionX_editText);
        editDynamicY = v.findViewById(R.id.editDynamicPositionY_editText);

        seekBarOpacity = v.findViewById(R.id.editButtonOpacity_seekbar);
        seekBarCornerRadius = v.findViewById(R.id.editCornerRadius_seekbar);
        seekBarStrokeWidth = v.findViewById(R.id.editStrokeWidth_seekbar);

        SeekBar.OnSeekBarChangeListener changeListener = new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(seekBar.equals(seekBarCornerRadius)) {
                    setPercentageText(textCornerRadius, i);
                    return;
                }
                if(seekBar.equals(seekBarOpacity)) {
                    setPercentageText(textOpacity, i);
                    return;
                }
                if(seekBar.equals(seekBarStrokeWidth)) {
                    setPercentageText(textStrokeWidth, i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {/*STUB*/}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {/*STUB*/}
        };

        //Add listeners, too bad I don't need all the methods
        seekBarOpacity.setOnSeekBarChangeListener(changeListener);
        seekBarCornerRadius.setOnSeekBarChangeListener(changeListener);
        seekBarStrokeWidth.setOnSeekBarChangeListener(changeListener);

        buttonBackgroundColor = v.findViewById(R.id.editBackgroundColor_imageButton);
        buttonStrokeColor = v.findViewById(R.id.editStrokeColor_imageButton);

        textOpacity = v.findViewById(R.id.editButtonOpacity_textView_percent);
        textCornerRadius = v.findViewById(R.id.editCornerRadius_textView_percent);
        textStrokeWidth = v.findViewById(R.id.editStrokeWidth_textView_percent);

        checkDynamicPosition = v.findViewById(R.id.checkboxDynamicPosition);
        checkDynamicPosition.setOnCheckedChangeListener((btn, checked) -> {
            editDynamicX.setEnabled(checked);
            editDynamicY.setEnabled(checked);
        });


        checkHoldAlt = v.findViewById(R.id.checkBoxKeyCombination_alt);
        checkHoldCtrl = v.findViewById(R.id.checkBoxKeyCombination_ctrl);
        checkHoldShift = v.findViewById(R.id.checkBoxKeyCombination_shift);

        //Initialize adapter for keycodes
        adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_item);
        String[] oldSpecialArr = ControlData.buildSpecialButtonArray();
        specialArr = new String[oldSpecialArr.length];
        for (int i = 0; i < specialArr.length; i++) {
            specialArr[i] = "SPECIAL_" + oldSpecialArr[specialArr.length - i - 1];
        }
        adapter.addAll(specialArr);
        adapter.addAll(AndroidLWJGLKeycode.generateKeyName());
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        for (Spinner spinner : spinnersKeycode) {
            spinner.setAdapter(adapter);
        }

        //Set color imageButton behavior
        buttonBackgroundColor.setOnClickListener(view -> ActionPopupWindow.showColorPicker(ctx, "Edit background color", true, ((ColorDrawable) buttonBackgroundColor.getBackground()).getColor(), buttonBackgroundColor));
        buttonStrokeColor.setOnClickListener(view -> ActionPopupWindow.showColorPicker(ctx, "Edit stroke color", false, ((ColorDrawable) buttonStrokeColor.getBackground()).getColor(), buttonStrokeColor));



        //Set dialog buttons behavior
        setupDialogButtons();

        hideUselessViews();

    }

    protected void setupDialogButtons(){
        //Set dialog buttons behavior
        builder.setPositiveButton(android.R.string.ok, (dialogInterface1, i) -> {
            if(!hasPropertiesErrors(dialog.getContext())){
                saveProperties();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
    }

    protected void hideUselessViews(){
        (v.findViewById(R.id.editOrientation_textView)).setVisibility(View.GONE);
    }

    protected void setEditDialogValues(){

        editName.setText(properties.name);

        checkToggle.setChecked(properties.isToggle);
        checkPassThrough.setChecked(properties.passThruEnabled);

        editWidth.setText(Float.toString(properties.getWidth()));
        editHeight.setText(Float.toString(properties.getHeight()));

        editDynamicX.setEnabled(properties.isDynamicBtn);
        editDynamicY.setEnabled(properties.isDynamicBtn);
        editDynamicX.setHint(Float.toString(properties.x));
        editDynamicX.setText(properties.dynamicX);
        editDynamicY.setHint(Float.toString(properties.y));
        editDynamicY.setText(properties.dynamicY);

        seekBarOpacity.setProgress((int) (properties.opacity*100));
        seekBarStrokeWidth.setProgress(properties.strokeWidth);
        seekBarCornerRadius.setProgress((int)properties.cornerRadius);

        buttonBackgroundColor.setBackgroundColor(properties.bgColor);
        buttonStrokeColor.setBackgroundColor(properties.strokeColor);

        setPercentageText(textCornerRadius,seekBarCornerRadius.getProgress());
        setPercentageText(textOpacity,seekBarOpacity.getProgress());
        setPercentageText(textStrokeWidth,seekBarStrokeWidth.getProgress());

        checkHoldAlt.setChecked(properties.holdAlt);
        checkHoldCtrl.setChecked(properties.holdCtrl);
        checkHoldShift.setChecked(properties.holdShift);
        checkDynamicPosition.setChecked(properties.isDynamicBtn);

        for(int i=0; i< properties.keycodes.length; i++){
            if (properties.keycodes[i] < 0) {
                spinnersKeycode[i].setSelection(properties.keycodes[i] + specialArr.length);
            } else {
                spinnersKeycode[i].setSelection(AndroidLWJGLKeycode.getIndexByLWJGLKey(properties.keycodes[i]) + specialArr.length);
            }
        }
    }


    protected boolean hasPropertiesErrors(Context ctx){
        if (editName.getText().toString().isEmpty()) {
            editName.setError(ctx.getResources().getString(R.string.global_error_field_empty));
            return true;
        }

        if (properties.isDynamicBtn) {

            int errorAt = 0;
            try {
                properties.insertDynamicPos(editDynamicX.getText().toString());
                errorAt = 1;
                properties.insertDynamicPos(editDynamicY.getText().toString());
            } catch (Throwable th) {
                (errorAt == 0 ? editDynamicX : editDynamicY).setError(th.getMessage());

                return true;
            }
        }

        return false;
    }

    protected void saveProperties(){
        //This method assumes there are no error.
        properties.name = editName.getText().toString();

        //Keycodes
        for(int i=0; i<spinnersKeycode.length; ++i){
            if (spinnersKeycode[i].getSelectedItemPosition() < specialArr.length) {
                properties.keycodes[i] = spinnersKeycode[i].getSelectedItemPosition() - specialArr.length;
            } else {
                properties.keycodes[i] = AndroidLWJGLKeycode.getKeyByIndex(spinnersKeycode[i].getSelectedItemPosition() - specialArr.length);
            }
        }

        properties.opacity = seekBarOpacity.getProgress()/100f;
        properties.strokeWidth = seekBarStrokeWidth.getProgress();
        properties.cornerRadius = seekBarCornerRadius.getProgress();

        properties.bgColor = ((ColorDrawable) buttonBackgroundColor.getBackground()).getColor();
        properties.strokeColor = ((ColorDrawable) buttonStrokeColor.getBackground()).getColor();

        properties.isToggle = checkToggle.isChecked();
        properties.passThruEnabled = checkPassThrough.isChecked();

        properties.setWidth(Float.parseFloat(editWidth.getText().toString()));
        properties.setHeight(Float.parseFloat(editHeight.getText().toString()));

        properties.isDynamicBtn = checkDynamicPosition.isChecked();
        properties.dynamicX = editDynamicX.getText().toString().isEmpty() ? properties.dynamicX = Float.toString(properties.x) : editDynamicX.getText().toString();
        properties.dynamicY = editDynamicY.getText().toString().isEmpty() ? properties.dynamicY = Float.toString(properties.y) : editDynamicY.getText().toString();

        properties.holdAlt = checkHoldAlt.isChecked();
        properties.holdCtrl = checkHoldCtrl.isChecked();
        properties.holdShift = checkHoldShift.isChecked();

        button.updateProperties();
    }

}
