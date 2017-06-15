package models;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;


public class Loader {
	
	
	public static Mesh loadToVAO(float[] positions) {
		//generate vao id and then bind that vao
		int vaoID = createVAO();
		//store the positions into a vbo
		storeDataInVBO(0,positions);
		//unbind the vao
		unbindVAO();
		//create a new mesh model
		return new Mesh(vaoID,positions.length / 3);
	}
	
	
	private static int createVAO() {
		//generate a vao id
		int vaoID = glGenVertexArrays();
		//bind the vao
		glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private static void storeDataInVBO(int attributeNumber, float[] data) {
		//generate a vbo id
		int vboID = glGenBuffers();
		//bind this vbo as an array buffer
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		//create a float buffer for the data
		FloatBuffer buffer = arrayToBuffer(data);
		//put this data into a glBuffer
		glBufferData(GL_ARRAY_BUFFER,buffer,GL_STATIC_DRAW);
		//establish a pointer to this data with the relevant attribute number
		glVertexAttribPointer(attributeNumber,3,GL11.GL_FLOAT,false,0,0);
		//unbind this buffer
		glBindBuffer(GL_ARRAY_BUFFER,0);
	}
	
	private static FloatBuffer arrayToBuffer(float[] data) {
		//create a float buffer with the same length as the data
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		//put the data into the buffer
		buffer.put(data);
		//flip the buffer from write to read mode
		buffer.flip();
		return buffer;
		
	}
	
	private static void unbindVAO() {
		//unbind the vao
		glBindVertexArray(0);
	}

}