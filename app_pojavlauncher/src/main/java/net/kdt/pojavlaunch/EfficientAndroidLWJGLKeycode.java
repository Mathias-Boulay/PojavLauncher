package net.kdt.pojavlaunch;

import android.view.KeyEvent;

import net.kdt.pojavlaunch.prefs.LauncherPreferences;

import org.lwjgl.glfw.CallbackBridge;

public class EfficientAndroidLWJGLKeycode {
    private static final int[] androidKeycodes = new int[103];
    private static final short[] LWJGLKeycodes = new short[androidKeycodes.length];
    private static String[] androidKeyNameArray; /* = new String[androidKeycodes.length]; */

    static {
        /*  BINARY SEARCH IS PERFORMED ON THE androidKeycodes ARRAY !
            WHEN ADDING A MAPPING, ADD IT SO THE androidKeycodes ARRAY STAYS SORTED ! */
        // Mapping Android Keycodes to LWJGL Keycodes
        
        add(KeyEvent.KEYCODE_UNKNOWN,LWJGLGLFWKeycode.GLFW_KEY_UNKNOWN);
        add(KeyEvent.KEYCODE_HOME, LWJGLGLFWKeycode.GLFW_KEY_HOME);
        // Escape key
        add(KeyEvent.KEYCODE_BACK, LWJGLGLFWKeycode.GLFW_KEY_ESCAPE);

        // 0-9 keys
        add(KeyEvent.KEYCODE_0, LWJGLGLFWKeycode.GLFW_KEY_0); //7
        add(KeyEvent.KEYCODE_1, LWJGLGLFWKeycode.GLFW_KEY_1);
        add(KeyEvent.KEYCODE_2, LWJGLGLFWKeycode.GLFW_KEY_2);
        add(KeyEvent.KEYCODE_3, LWJGLGLFWKeycode.GLFW_KEY_3);
        add(KeyEvent.KEYCODE_4, LWJGLGLFWKeycode.GLFW_KEY_4);
        add(KeyEvent.KEYCODE_5, LWJGLGLFWKeycode.GLFW_KEY_5);
        add(KeyEvent.KEYCODE_6, LWJGLGLFWKeycode.GLFW_KEY_6);
        add(KeyEvent.KEYCODE_7, LWJGLGLFWKeycode.GLFW_KEY_7);
        add(KeyEvent.KEYCODE_8, LWJGLGLFWKeycode.GLFW_KEY_8);
        add(KeyEvent.KEYCODE_9, LWJGLGLFWKeycode.GLFW_KEY_9); //16

        add(KeyEvent.KEYCODE_POUND,LWJGLGLFWKeycode.GLFW_KEY_3);

        // Arrow keys
        add(KeyEvent.KEYCODE_DPAD_UP, LWJGLGLFWKeycode.GLFW_KEY_UP); //19
        add(KeyEvent.KEYCODE_DPAD_DOWN, LWJGLGLFWKeycode.GLFW_KEY_DOWN);
        add(KeyEvent.KEYCODE_DPAD_LEFT, LWJGLGLFWKeycode.GLFW_KEY_LEFT);
        add(KeyEvent.KEYCODE_DPAD_RIGHT, LWJGLGLFWKeycode.GLFW_KEY_RIGHT); //22
        
        // A-Z keys
        add(KeyEvent.KEYCODE_A, LWJGLGLFWKeycode.GLFW_KEY_A); //29
        add(KeyEvent.KEYCODE_B, LWJGLGLFWKeycode.GLFW_KEY_B);
        add(KeyEvent.KEYCODE_C, LWJGLGLFWKeycode.GLFW_KEY_C);
        add(KeyEvent.KEYCODE_D, LWJGLGLFWKeycode.GLFW_KEY_D);
        add(KeyEvent.KEYCODE_E, LWJGLGLFWKeycode.GLFW_KEY_E);
        add(KeyEvent.KEYCODE_F, LWJGLGLFWKeycode.GLFW_KEY_F);
        add(KeyEvent.KEYCODE_G, LWJGLGLFWKeycode.GLFW_KEY_G);
        add(KeyEvent.KEYCODE_H, LWJGLGLFWKeycode.GLFW_KEY_H);
        add(KeyEvent.KEYCODE_I, LWJGLGLFWKeycode.GLFW_KEY_I);
        add(KeyEvent.KEYCODE_J, LWJGLGLFWKeycode.GLFW_KEY_J);
        add(KeyEvent.KEYCODE_K, LWJGLGLFWKeycode.GLFW_KEY_K);
        add(KeyEvent.KEYCODE_L, LWJGLGLFWKeycode.GLFW_KEY_L);
        add(KeyEvent.KEYCODE_M, LWJGLGLFWKeycode.GLFW_KEY_M);
        add(KeyEvent.KEYCODE_N, LWJGLGLFWKeycode.GLFW_KEY_N);
        add(KeyEvent.KEYCODE_O, LWJGLGLFWKeycode.GLFW_KEY_O);
        add(KeyEvent.KEYCODE_P, LWJGLGLFWKeycode.GLFW_KEY_P);
        add(KeyEvent.KEYCODE_Q, LWJGLGLFWKeycode.GLFW_KEY_Q);
        add(KeyEvent.KEYCODE_R, LWJGLGLFWKeycode.GLFW_KEY_R);
        add(KeyEvent.KEYCODE_S, LWJGLGLFWKeycode.GLFW_KEY_S);
        add(KeyEvent.KEYCODE_T, LWJGLGLFWKeycode.GLFW_KEY_T);
        add(KeyEvent.KEYCODE_U, LWJGLGLFWKeycode.GLFW_KEY_U);
        add(KeyEvent.KEYCODE_V, LWJGLGLFWKeycode.GLFW_KEY_V);
        add(KeyEvent.KEYCODE_W, LWJGLGLFWKeycode.GLFW_KEY_W);
        add(KeyEvent.KEYCODE_X, LWJGLGLFWKeycode.GLFW_KEY_X);
        add(KeyEvent.KEYCODE_Y, LWJGLGLFWKeycode.GLFW_KEY_Y);
        add(KeyEvent.KEYCODE_Z, LWJGLGLFWKeycode.GLFW_KEY_Z); //54


        add(KeyEvent.KEYCODE_COMMA, LWJGLGLFWKeycode.GLFW_KEY_COMMA);
        
        // Alt keys
        add(KeyEvent.KEYCODE_ALT_LEFT, LWJGLGLFWKeycode.GLFW_KEY_LEFT_ALT);
        add(KeyEvent.KEYCODE_ALT_RIGHT, LWJGLGLFWKeycode.GLFW_KEY_RIGHT_ALT);

        // Shift keys
        add(KeyEvent.KEYCODE_SHIFT_LEFT, LWJGLGLFWKeycode.GLFW_KEY_LEFT_SHIFT);
        add(KeyEvent.KEYCODE_SHIFT_RIGHT, LWJGLGLFWKeycode.GLFW_KEY_RIGHT_SHIFT);

        add(KeyEvent.KEYCODE_TAB, LWJGLGLFWKeycode.GLFW_KEY_TAB);
        add(KeyEvent.KEYCODE_SPACE, LWJGLGLFWKeycode.GLFW_KEY_SPACE);
        add(KeyEvent.KEYCODE_ENTER, LWJGLGLFWKeycode.GLFW_KEY_ENTER); //66
        add(KeyEvent.KEYCODE_DEL, LWJGLGLFWKeycode.GLFW_KEY_BACKSPACE); // Backspace
        add(KeyEvent.KEYCODE_GRAVE, LWJGLGLFWKeycode.GLFW_KEY_GRAVE_ACCENT);
        add(KeyEvent.KEYCODE_MINUS, LWJGLGLFWKeycode.GLFW_KEY_MINUS);
        add(KeyEvent.KEYCODE_EQUALS, LWJGLGLFWKeycode.GLFW_KEY_EQUAL);
        add(KeyEvent.KEYCODE_LEFT_BRACKET, LWJGLGLFWKeycode.GLFW_KEY_LEFT_BRACKET);
        add(KeyEvent.KEYCODE_RIGHT_BRACKET, LWJGLGLFWKeycode.GLFW_KEY_RIGHT_BRACKET);
        add(KeyEvent.KEYCODE_BACKSLASH, LWJGLGLFWKeycode.GLFW_KEY_BACKSLASH);
        add(KeyEvent.KEYCODE_SEMICOLON, LWJGLGLFWKeycode.GLFW_KEY_SEMICOLON); //74

        add(KeyEvent.KEYCODE_SLASH, LWJGLGLFWKeycode.GLFW_KEY_SLASH); //76
        add(KeyEvent.KEYCODE_AT,LWJGLGLFWKeycode.GLFW_KEY_2);

        add(KeyEvent.KEYCODE_PLUS, LWJGLGLFWKeycode.GLFW_KEY_KP_ADD);

        // Page keys
        add(KeyEvent.KEYCODE_PAGE_UP, LWJGLGLFWKeycode.GLFW_KEY_PAGE_UP); //92
        add(KeyEvent.KEYCODE_PAGE_DOWN, LWJGLGLFWKeycode.GLFW_KEY_PAGE_DOWN);

        add(KeyEvent.KEYCODE_ESCAPE, LWJGLGLFWKeycode.GLFW_KEY_ESCAPE);

        // Control keys
        add(KeyEvent.KEYCODE_CTRL_LEFT, LWJGLGLFWKeycode.GLFW_KEY_LEFT_CONTROL);
        add(KeyEvent.KEYCODE_CTRL_RIGHT, LWJGLGLFWKeycode.GLFW_KEY_RIGHT_CONTROL);

        add(KeyEvent.KEYCODE_CAPS_LOCK, LWJGLGLFWKeycode.GLFW_KEY_CAPS_LOCK);
        add(KeyEvent.KEYCODE_BREAK, LWJGLGLFWKeycode.GLFW_KEY_PAUSE);
        add(KeyEvent.KEYCODE_INSERT, LWJGLGLFWKeycode.GLFW_KEY_INSERT);
        
        // Fn keys
        add(KeyEvent.KEYCODE_F1, LWJGLGLFWKeycode.GLFW_KEY_F1); //131
        add(KeyEvent.KEYCODE_F2, LWJGLGLFWKeycode.GLFW_KEY_F2);
        add(KeyEvent.KEYCODE_F3, LWJGLGLFWKeycode.GLFW_KEY_F3);
        add(KeyEvent.KEYCODE_F4, LWJGLGLFWKeycode.GLFW_KEY_F4);
        add(KeyEvent.KEYCODE_F5, LWJGLGLFWKeycode.GLFW_KEY_F5);
        add(KeyEvent.KEYCODE_F6, LWJGLGLFWKeycode.GLFW_KEY_F6);
        add(KeyEvent.KEYCODE_F7, LWJGLGLFWKeycode.GLFW_KEY_F7);
        add(KeyEvent.KEYCODE_F8, LWJGLGLFWKeycode.GLFW_KEY_F8);
        add(KeyEvent.KEYCODE_F9, LWJGLGLFWKeycode.GLFW_KEY_F9);
        add(KeyEvent.KEYCODE_F10, LWJGLGLFWKeycode.GLFW_KEY_F10);
        add(KeyEvent.KEYCODE_F11, LWJGLGLFWKeycode.GLFW_KEY_F11);
        add(KeyEvent.KEYCODE_F12, LWJGLGLFWKeycode.GLFW_KEY_F12); //142
        
        // Num keys
        add(KeyEvent.KEYCODE_NUM_LOCK, LWJGLGLFWKeycode.GLFW_KEY_NUM_LOCK); //143
        add(KeyEvent.KEYCODE_NUMPAD_0, LWJGLGLFWKeycode.GLFW_KEY_0);
        add(KeyEvent.KEYCODE_NUMPAD_1, LWJGLGLFWKeycode.GLFW_KEY_1);
        add(KeyEvent.KEYCODE_NUMPAD_2, LWJGLGLFWKeycode.GLFW_KEY_2);
        add(KeyEvent.KEYCODE_NUMPAD_3, LWJGLGLFWKeycode.GLFW_KEY_3);
        add(KeyEvent.KEYCODE_NUMPAD_4, LWJGLGLFWKeycode.GLFW_KEY_4);
        add(KeyEvent.KEYCODE_NUMPAD_5, LWJGLGLFWKeycode.GLFW_KEY_5);
        add(KeyEvent.KEYCODE_NUMPAD_6, LWJGLGLFWKeycode.GLFW_KEY_6);
        add(KeyEvent.KEYCODE_NUMPAD_7, LWJGLGLFWKeycode.GLFW_KEY_7);
        add(KeyEvent.KEYCODE_NUMPAD_8, LWJGLGLFWKeycode.GLFW_KEY_8);
        add(KeyEvent.KEYCODE_NUMPAD_9, LWJGLGLFWKeycode.GLFW_KEY_9);
        add(KeyEvent.KEYCODE_NUMPAD_DIVIDE, LWJGLGLFWKeycode.GLFW_KEY_KP_DIVIDE);
        add(KeyEvent.KEYCODE_NUMPAD_MULTIPLY, LWJGLGLFWKeycode.GLFW_KEY_KP_MULTIPLY);
        add(KeyEvent.KEYCODE_NUMPAD_SUBTRACT, LWJGLGLFWKeycode.GLFW_KEY_KP_SUBTRACT);
        add(KeyEvent.KEYCODE_NUMPAD_ADD, LWJGLGLFWKeycode.GLFW_KEY_KP_ADD);
        add(KeyEvent.KEYCODE_NUMPAD_DOT, LWJGLGLFWKeycode.GLFW_KEY_PERIOD);
        add(KeyEvent.KEYCODE_NUMPAD_COMMA, LWJGLGLFWKeycode.GLFW_KEY_COMMA);
        add(KeyEvent.KEYCODE_NUMPAD_ENTER, LWJGLGLFWKeycode.GLFW_KEY_ENTER);
        add(KeyEvent.KEYCODE_NUMPAD_EQUALS, LWJGLGLFWKeycode.GLFW_KEY_EQUAL); //161

    }

    private static short index = 0;
    private static void add(int androidKeycode, short LWJGLKeycode){
        androidKeycodes[index] = androidKeycode;
        LWJGLKeycodes[index] = LWJGLKeycode;
        ++index;
    }

    public static short get(int key){
        //Taken from: https://www.geeksforgeeks.org/binary-search/
        //Give the value associated to a key
        int left = 0, right = androidKeycodes.length - 1;
        while (left <= right) {
            int m = left + (right - left) / 2;

            // Check if x is present at mid
            if (androidKeycodes[m] == key)
                return LWJGLKeycodes[m];

            // If x greater, ignore left half
            if (androidKeycodes[m] < key)
                left = m + 1;

                // If x is smaller, ignore right half
            else
                right = m - 1;
        }

        // if we reach here, then element was
        // not present
        return -1;
    }

    public static boolean containsKey(int keycode){
        return get(keycode) != -1;
    }


    public static String[] generateKeyName() {
        if (androidKeyNameArray == null) {
            androidKeyNameArray = new String[androidKeycodes.length];
            for(int i=0; i < androidKeyNameArray.length; ++i){
                androidKeyNameArray[i] = KeyEvent.keyCodeToString(androidKeycodes[i]).replace("KEYCODE_", "");
            }
        }
        return androidKeyNameArray;
    }
    
    public static void execKey(KeyEvent keyEvent, int i, boolean isDown) {
        CallbackBridge.holdingAlt = keyEvent.isAltPressed();
        CallbackBridge.holdingCapslock = keyEvent.isCapsLockOn();
        CallbackBridge.holdingCtrl = keyEvent.isCtrlPressed();
        CallbackBridge.holdingNumlock = keyEvent.isNumLockOn();
        CallbackBridge.holdingShift = keyEvent.isShiftPressed();

        try {
                System.out.println(keyEvent.getKeyCode() + " " +keyEvent.getDisplayLabel());
            if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK && LauncherPreferences.PREF_BACK_TO_RIGHT_MOUSE) {
                BaseMainActivity.sendMouseButton(LWJGLGLFWKeycode.GLFW_MOUSE_BUTTON_RIGHT, keyEvent.getAction() == KeyEvent.ACTION_DOWN);
            } else {
                if(keyEvent.getUnicodeChar() != 0) {
                    char key = (char)keyEvent.getUnicodeChar();
                     BaseMainActivity.sendKeyPress(
                             get(keyEvent.getKeyCode()),
                             key,
                             0,
                             CallbackBridge.getCurrentMods(),
                             keyEvent.getAction() == KeyEvent.ACTION_DOWN);
                }else{
                     BaseMainActivity.sendKeyPress(
                             get(keyEvent.getKeyCode()),
                             CallbackBridge.getCurrentMods(),
                             keyEvent.getAction()==KeyEvent.ACTION_DOWN);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void execKeyIndex(BaseMainActivity mainActivity, int index) {
        mainActivity.sendKeyPress(getKeyByIndex(index));

    }
    
    public static int getKeyByIndex(int index) {
        return LWJGLKeycodes[index];
    }

    public static int getIndexByLWJGLKey(int lwjglKey) {
        for (int i = 0; i < LWJGLKeycodes.length; i++) {
            if(LWJGLKeycodes[i] == lwjglKey) return i;
        }
        
        return 0;
    }
}
