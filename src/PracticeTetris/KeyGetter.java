package PracticeTetris;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public class KeyGetter {

	public static HashMap<String, Integer> keys;
	public static ArrayList<String> keyNames;

	public static void loadKeys() {
		keys = new HashMap<String, Integer>();
		keyNames = new ArrayList<String>();
		Field[] field = KeyEvent.class.getFields();
		// Field는 다른 클래스에 있는 변수들의 정보를 제공해주는 클래스이다. 관련자료 : https://12bme.tistory.com/129
		for (Field f : field) {
			if (Modifier.isStatic(f.getModifiers())) {
				// Modifier.isStatic 은 클래스에 선언된 정적 필드만 검색합니다.
				// getModifiers는 해당 변수에 접근자 정보를 리턴합니다.
				if (f.getName().startsWith("VK")) {
					// startswith String클래스의 메소드이며 인자로 넣은 Strign값으로 접두어가 시작하는지 확인하는 메소드입니다
					try {

						int num = f.getInt(null);
						// getint에 대한 설명은
						// https://www.tutorialspoint.com/javareflect/javareflect_field_getint.htm
						String name = KeyEvent.getKeyText(f.getInt(null));
						keys.put(name, num);
						keyNames.add(name);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
