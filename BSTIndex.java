/*
THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS OR COPIED FROM ONLINE RESOURCES.
Allison_Ng
*/

import java.util.ArrayList;

public class BSTIndex {

		private class Node {
			private String key;
			private MovieInfo data;
			private Node left, right;

			public Node(MovieInfo info) {
				data = info;
				key = data.shortTitle;
			}
		}

		private Node root;
		private int size;


		public BSTIndex() {
			root = null;
			size = 0;
		}

		// --------- [DO NOT MODIFY!] public BST methods  --------- //
		/* Important: Notice that each public method here calls another private method while passing it a reference to the tree root. This is a common way of structuring BST functions such that external client code will not have direct access to the tree root. You will be implementing the code in the private methods, not the public ones. */

		/* Calculate and return the height of the current tree. */
		public int calcHeight(){
			return calcNodeHeight(this.root);
		}

		/* Insert the given data element into the proper place in the BST structure. */
		public void insertMovie(MovieInfo data) {
			size++;
			root = insertMovie(data, this.root);
		}

		/* Find and return the data element (i.e. the MovieInfo object)
		of the node where the movie's shortTitle matches the given key.
		Return null if the movie is not found. */
		public MovieInfo findMovie(String key) {
			return findMovie(this.root, key);
		}

		/* Traverse the tree in order based on the keys. The ArrayList
		returned should contain movie IDs based on the order of 
		the keys (i.e., shortTitle) in the tree. */
		public ArrayList<Integer> inorderTraversal() {
			ArrayList<Integer> movieList = new ArrayList<Integer>();
			inorderTraversal(this.root, movieList);
			return movieList;
		}

		/* Get all movies (full title) in the database whose shortTitle begins with
		the passed prefix string. If no movies match the given prefix, the 
		ArrayList should be empty. The order of the movies in the list does not matter,
		but make sure each movie is a separate element in the list. */
		public ArrayList<String> getMoviesPrefix(String prefix) {
			ArrayList<String> movieList = new ArrayList<String>();
			getMoviesPrefix(this.root, prefix, movieList);
			return movieList;
		}

		public int getSize() {
			return size;
		}


		// ----------------- end of public methods ------------------ //


		// ------------- private BST methods --------------- //
		private int calcNodeHeight(Node t) {
			if (t == null) { //checks if the node is null, and returns 0 if the BST is empty
				return 0;
			}
			
			//returns the height of the larger subtree between the left and right, + 1 to account for the root
			return 1 + Math.max (calcNodeHeight (t.left), calcNodeHeight (t.right)); //recursively calls method to traverse down left and right subtrees to get height
		}

		private Node insertMovie(MovieInfo data, Node t) {
			if (t == null) { //checks if the node is null, and returns a new node (creates the root of the BST) if the BST is empty
				return new Node (data); //returns new node (root)
			} else {
				if (data.shortTitle.compareTo (t.key) < 0) { //checks whether or not the short title of the given data is less than (shorter than) the key of the node
					t.left = insertMovie (data, t.left); //creates a new left node with the given data by recursively calling the method
				} else if (data.shortTitle.compareTo(t.key) > 0) { //checks whether or not short title of the given data is greater than (longer than) the key of the node
					t.right = insertMovie (data, t.right); //creates a new right node with the given data by recursively calling the method
				}
				
				return t; //returns the node
			}
		}

		private MovieInfo findMovie(Node t, String key) {
			if (t == null) { //checks if the node is null, and returns null if the BST is empty (as no movie can be found)
				return null;
			} else {
				if (key.compareTo(t.key) < 0) { //conditional checking whether or not the given key is less than (shorter than) the key of the node 
					return findMovie (t.left, key); //recursively calls method to traverse down left subtree
				} else if (key.compareTo(t.key) > 0) {
					return findMovie (t.right, key); //recursively calls method to traverse down right subtree
				}
				
				return t.data; //returns the data of the given node
			}
		}

		private void getMoviesPrefix(Node t, String prefix, ArrayList<String> movieList) {
			if (t == null) { //checks if the node is null, and returns if there is no next movie (at the end of the BST)
				return;
			} else {
				int length = prefix.length(); //variable "length" which holds the length of the prefix
				String temp = prefix.substring(0, length - 1); //Temporary string that holds the prefix
				if (t.key.startsWith(temp)) { //checks whether or not the key of the given node starts with the prefix
					movieList.add(t.data.fullTitle); //adds the movie to the Arraylist if the title starts with the prefix
				}
				
				if (t.left != null) { //checks if the left node is null (if there is a left node)
					getMoviesPrefix (t.left, prefix, movieList); //recursively calls method to traverse down left subtree & check for the prefix
				}
				
				if (t.right != null) { //checks if the right node is null (if there is a right node)
					getMoviesPrefix (t.right, prefix, movieList); //recursively calls method to traverse down right subtree & check for the prefix
				}
				
			}
		}

		private void inorderTraversal(Node t, ArrayList<Integer> movieList) {
			if (t == null) { //checks if the node is null, and returns if the there is no next movie (at the end of the BST)
				return;
			} else {
				inorderTraversal (t.left, movieList); //recursively calls method to traverse down left subtree
				
				movieList.add(t.data.ID); //adds movie ID to the Arraylist
				
				inorderTraversal (t.right, movieList); //recursively calls method to traverse down right subtree
			}
		}

}
