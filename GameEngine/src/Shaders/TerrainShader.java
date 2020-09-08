package Shaders;

import org.lwjgl.util.vector.Matrix4f;

import entities.Camera;
import entities.Light;
import toolbox.Maths;

public class TerrainShader extends ShaderProgram {
	
	private static final String vertexFileS = "src/Shaders/terrainVertexShader.txt";
	private static final String fragmentFileS = "src/Shaders/terrainFragmentShader.txt";
	
	private int location_transMatrix;
	private int location_projMatrix;
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColour;
	private int location_shineDamper;
	private int location_reflectivity;

	public TerrainShader() {
		super(vertexFileS, fragmentFileS);
		
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transMatrix = super.getUniformLocation("transMatrix");	
		location_projMatrix = super.getUniformLocation("projectionMatrix");	
		location_viewMatrix = super.getUniformLocation("viewMatrix");	
		location_lightColour = super.getUniformLocation("lightColour");
		location_lightPosition = super.getUniformLocation("lightPosition");
		location_shineDamper = super.getUniformLocation("shineDamper");
		location_reflectivity = super.getUniformLocation("reflectivity");
	}
	
	public void loadShineVariables(float damper, float reflectivity) {
		super.loadFloat(location_shineDamper, damper);
		super.loadFloat(location_reflectivity, reflectivity);
		
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transMatrix, matrix);
	}
	
	public void loadLight(Light light) {
		super.loadVector(location_lightPosition, light.getPosition());
		super.loadVector(location_lightColour, light.getColour());
	}
	
	public void loadProjMatrix(Matrix4f projection) {
		super.loadMatrix(location_projMatrix, projection);
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMatrix = Maths.createViewCamera(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}

}
