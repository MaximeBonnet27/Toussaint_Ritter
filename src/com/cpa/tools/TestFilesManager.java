package com.cpa.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.cpa.geometry.Vertex;

public class TestFilesManager {

	private static TestFilesManager instance;
	private int currentIndex;
	private File directory;

	private String currentFileName;

	private TestFilesManager(){
		directory = new File("samples/");
		currentIndex = 0;

	}

	public static TestFilesManager getInstance(){
		if(instance == null){
			instance = new TestFilesManager();
		}
		return instance;
	}

	public ArrayList<Vertex> getPointsFromFile(String filename){
		ArrayList<Vertex> points = new ArrayList<Vertex>();
		try{
			BufferedReader bReader = new BufferedReader(new FileReader(filename));
			String line;
			String[] coordinates;
			while((line = bReader.readLine()) != null){
				coordinates = line.split(" ");
				points.add(new Vertex(Integer.parseInt(coordinates[0]),
						Integer.parseInt(coordinates[1])));
			}
		}
		catch(IOException e){
			e.printStackTrace(System.err);

		}
		return points;
	}

	public ArrayList<Vertex> getRandomList(){
		Random r = new Random();
		ArrayList<Vertex> resultat = new ArrayList<Vertex>();
		for(int i = 0; i < 256; ++i){
			resultat.add(new Vertex(r.nextDouble() * 200 + 300,r.nextDouble() * 200 + 100));
		}
		return resultat;
	}

	public ArrayList<Vertex> getNextFile(){
		currentIndex = (currentIndex + 1);
		if(currentIndex >= directory.listFiles().length) return null;
		while(!(directory.listFiles()[currentIndex].isFile())){
			currentIndex = (currentIndex + 1);
			if(currentIndex >= directory.listFiles().length) return null;
		}
		currentFileName = directory.listFiles()[currentIndex].getName();
		return getPointsFromFile("samples/" + directory.listFiles()[currentIndex].getName());
	}

	public String getCurrentFileName() {
		return currentFileName;
	}

}