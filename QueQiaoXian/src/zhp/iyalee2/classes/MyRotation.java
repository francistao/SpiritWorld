package zhp.iyalee2.classes;

import com.metaio.sdk.jni.Rotation;

public class MyRotation {
	float x, y, z;

	public MyRotation(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Rotation toRotation(){
		return new Rotation(x, y, z);
	}
	
	
}
