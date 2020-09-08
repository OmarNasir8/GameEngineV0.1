package textures;

public class ModelTexture {
	
	private int textureID;
	private float shineDampner = 1;
	private float reflectivity = 0;
	
	public ModelTexture(int id) {
		textureID = id;
	}
	
	public int getID() {
		return textureID;
	}

	public float getShineDampner() {
		return shineDampner;
	}

	public void setShineDampner(float shineDampner) {
		this.shineDampner = shineDampner;
	}

	public float getReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}
	
	

}
