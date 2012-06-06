/*
 * Copyright 2005 - 2009 by KNURT Systeme (http://www.knurt.de)
 *
 * Licensed under the Creative Commons License Attribution-NonCommercial 3.0 Unported;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://creativecommons.org/licenses/by-nc/3.0/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.knurt.heinzelmann.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Pattern;

/*
 * Created on Feb 20, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * @author danieloltmanns
 * 
 *         TODO rework comment
 * @since 0.20050220
 * @version 0.20091104
 */
public class Fileops {

	private FileInputStream fis;
	private BufferedInputStream bis;
	private FileOutputStream fos;
	private BufferedOutputStream bos;
	private byte[] bytes;
	private String dirs = "";
	private Vectorops vo = new Vectorops();

	/**
	 * creates the given path of the pathname and all its unexisting parentdirs.
	 * 
	 * @param pathname
	 *            - of the path to create
	 */
	public void createFolder(String pathname) {
		Pattern p = Pattern.compile(File.separator);
		String[] folders = p.split(pathname);
		String path = "";
		if (pathname.startsWith(File.separator)) {
			path += File.separator;
		}
		for (int i = 0; i < folders.length; i++) {
			path += folders[i];
			File f = new File(path);
			if (!f.exists()) {
				f.mkdir();
			}
			path += File.separator;
		}
	}

	/**
	 * copies directories, files with or without subdirectories into another
	 * directory
	 * 
	 * @param sourcePath
	 *            - being copied. if subdirectory is true, all directories and
	 *            its content are copied too.
	 * @param subdirs
	 *            - true, if subdirectories shall be copied too
	 * @param targetPath
	 *            = path, where everything shall be copied in
	 */
	public void copyDirs(String sourcePath, boolean subdirs, String targetPath) {

		File tmpF = new File(sourcePath);
		File[] files = tmpF.listFiles();

		// kopiert alle dateien
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				copyFile(files[i], targetPath + dirs);
			}
		}
		// �berpr�ft das verzeichnis auf einen ordner und ruft ggf. die methode
		// rekursiv mit diesen ordnern auf
		if (subdirs) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					dirs = File.separator + files[i].getPath().substring(sourcePath.length());
					createFolder(targetPath + File.separator + dirs);
					copyDirs(files[i].getPath(), subdirs, targetPath); // rekursion:
					// tu
					// das
					// gleich
					// mit
					// diesem
					// verzeichnis
				}
			}
		}
	}

	/**
	 * copies a file to the given path
	 * 
	 * @param sourcePath
	 *            - file to copy
	 * @param targetPath
	 *            - path to copy in
	 * @return true - if copying was sucessful
	 */
	public boolean copyFile(File sourcePath, String targetPath) {
		boolean copied = false;
		try {
			fis = new FileInputStream(sourcePath);
			bis = new BufferedInputStream(fis);

			fos = new FileOutputStream(targetPath + File.separator + sourcePath.getName());
			bos = new BufferedOutputStream(fos);

			bytes = new byte[fis.available()];
			bis.read(bytes, 0, bytes.length);
			bos.write(bytes);

			bos.flush();
			bos.close();
			bis.close();
			copied = true;
		} catch (IOException io) {
			System.out.println(io);
		}
		return copied;

	}

	/**
	 * moves a file to the given path
	 * 
	 * @param sourcePath
	 *            - file to move
	 * @param targetPath
	 *            - path to move in
	 * @return true - if moving was sucessful
	 */
	public boolean moveFile(File sourcePath, String targetPath) {
		boolean copied = false;
		sourcePath.deleteOnExit();
		try {
			fis = new FileInputStream(sourcePath);
			bis = new BufferedInputStream(fis);

			fos = new FileOutputStream(targetPath + File.separator + sourcePath.getName());
			bos = new BufferedOutputStream(fos);

			bytes = new byte[fis.available()];
			bis.read(bytes, 0, bytes.length);
			bos.write(bytes);

			bos.flush();
			bos.close();
			bis.close();
			copied = true;
		} catch (IOException io) {
			System.out.println(io);
		}
		return copied;
	}

	/**
	 * returns a file-array of all directories in the given directories TODO
	 * this method is quiet complex ( about > x^2 )
	 * 
	 * @param parents
	 *            - directories to search in
	 * @return file-array with all directories in parent directories in
	 *         subdirectories
	 */
	public File[] getAllChildrendirs(File[] parents) {
		Vector<File> a = new Vector<File>(); // saves all dirs
		File[] fTmp;

		// if there is a file, filter all files
		for (int i = 0; i < parents.length; i++) {
			if (!parents[i].isDirectory()) {
				parents = getDirectories(parents);
				continue;
			}
		}

		for (int i = 0; i < parents.length; i++) {
			a.add(parents[i]); // add directory itself

			// list all directories in the directory
			fTmp = parents[i].listFiles(new java.io.FileFilter() {

				public boolean accept(File f) {
					return f.isDirectory();
				}
			});

			// put directory in the vector, if it doesn't already exists in
			// parents
			for (int j = 0; j < fTmp.length; j++) {

				boolean stillExists = false;

				// checks, if the file is already existing
				for (int k = 0; k < parents.length; k++) {
					if (parents[k].compareTo(fTmp[j]) == 0) {
						stillExists = true;
					}
				}

				if (!stillExists) {
					a.add(fTmp[j]);
				}
			}
		}

		// puts vector into an file-array
		File[] x = vo.vectorToFilearray(a);

		// return x, if no more directory was found, else recursion
		if (x.length == parents.length) { // no more dirs
			return x;
		} else {
			return getAllChildrendirs(x);
		}
	}

	public static String getTextFile(File file) throws FileNotFoundException, IOException {
		String tmpString = "";
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			tmpString += br.readLine();
		}
		return tmpString;
	}

	/**
	 * returns an array of the given files without the non-directories in it
	 * 
	 * @param files
	 *            - shall be filtered
	 * @return - file-array with non-directories
	 */
	public File[] getDirectories(File[] files) {
		Vector<File> tmp = new Vector<File>(); // filters dirs from parents
		// only dirs in the tmp vector
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				tmp.add(files[i]);
			}
		}

		// puts vector into an file-array
		File[] tmpX = vo.vectorToFilearray(tmp);

		return tmpX; // parents now just dirs

	}
	// public static void main(String[] args) {
	// String quelle =
	// /*"Macintosh HD"+*/File.separator+"Users"+File.separator+"danieloltmanns"+File.separator+"test"+File.separator+"quelle";
	// String ziel =
	// /*"Macintosh HD"+*/File.separator+"Users"+File.separator+"danieloltmanns"+File.separator+"test"+File.separator+"ziel";
	// Fileops fo = new Fileops();
	// fo.copyDirs(quelle, true, ziel);
	// }
}
