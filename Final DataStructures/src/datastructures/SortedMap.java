package datastructures;

import java.util.Iterator;

import datastructures.Map.MapIterator;

/**
 * A Map class which implements a Map using a Binary Search Tree
 * 
 * @author Brad McLean 0103571
 *
 * @param <K> The generic key type.
 * @param <V> The generic value.
 */
public class SortedMap<K extends Comparable, V> implements Map<K, V> {

	// Node class contains a Pair(key, value)
	// and two children node.
	public class Node {
		private Pair<K, V> data;
		private Node left;
		private Node right;

		// 3 Node constructors depending on what is needed
		public Node() {
			data = null;
			left = right = null;
		}

		public Node(Pair pair) {
			data = pair;
			left = right = null;
		}

		public Node(K key, V value) {
			Pair<K, V> pair = new Pair(key, value);
			data = pair;
			left = right = null;
		}

		//
		public Pair<K, V> getData() {
			return data;
		}

		private boolean isLeaf() {
			if (this.left == null && this.right == null)
				return true;
			return false;
		}
	}

	/**
	 * 
	 * Iterator class to run through a BST in order of increasing keys.
	 *
	 */
	public class SortedMapIterator extends MapIterator {
		private Node node;

		public SortedMapIterator() {
			node = null;
		}

		public SortedMapIterator(Node node) {
			this.node = node;
		}

		/**
		 * Gets the current node of the iterator.
		 * 
		 * @return The current node.
		 */
		public Node getNode() {
			return node;
		}

		@Override
		/**
		 * Determines if the iterator has a next node.
		 * 
		 * @retrun True if the next node is not null.
		 */
		public boolean hasNext() {
			if (this.peek() == null) {
				return false;
			}
			return true;
		}

		// Method to see the next node without advancing the iterator.
		private Node peek() {

			SortedMapIterator itr = new SortedMapIterator(this.node);
			if (itr.node.right != null) {
				itr.node = itr.node.right;
				while (itr.node.left != null)
					itr.node = itr.node.left;
				return itr.node;
			}

			// look for a parent
			Node parent;
			parent = forwardCheck(root);
			return parent;

		}

		@Override
		/**
		 * Advances the iterator to the next node and returns the data in the node.
		 * 
		 * @return The data in the next node.
		 */
		public Pair<K, V> next() {
			this.node = this.peek();
			if (this.node == null)
				return null;
			return this.node.data;

		}

		// returns the parent of the current node
		private Node forwardCheck(Node node) {
			int difference = this.node.data.first.compareTo(node.data.first);
			if (difference == 0) { // node has been found

				return null;
			}

			Node parent = null;
			if (difference < 0) {
				parent = forwardCheck(node.left);
				if (parent == null)
					return node;
				return parent;
			}
			parent = forwardCheck(node.right);
			if (parent == null)
				return null;
			return parent;

		}

	}

	/**
	 * 
	 * Iterator class to run through a BST in order of decreasing keys.
	 *
	 */
	public class SortedMapReverseIterator extends MapReverseIterator {

		private Node node;

		public SortedMapReverseIterator() {
			node = null;
		}

		public SortedMapReverseIterator(Node node) {
			this.node = node;
		}

		/**
		 * Gets the current node of the iterator.
		 * 
		 * @return The current node.
		 */
		public Node getNode() {
			return node;
		}

		/**
		 * Determines if the iterator has a previous node.
		 * 
		 * @return True if the previous node is not null.
		 */
		@Override
		public boolean hasNext() {
			if (this.peek() == null) {
				return false;
			}
			return true;
		}

		private Node peek() {

			SortedMapReverseIterator itr = new SortedMapReverseIterator(this.node);
			if (itr.node.left != null) {
				itr.node = itr.node.left;
				while (itr.node.right != null)
					itr.node = itr.node.right;
				return itr.node;
			}

			// look for a parent
			Node parent;
			parent = forwardCheck(root);
			return parent;

		}

		@Override
		/**
		 * Advances the iterator to the previous node and returns the data in the node.
		 * 
		 * @return The data in the previous node.
		 */
		public Pair<K, V> next() {
			this.node = this.peek();
			if (this.node == null)
				return null;
			return this.node.data;

		}

		// returns the parent of the current node
		public Node forwardCheck(Node node) {
			int difference = this.node.data.first.compareTo(node.data.first);
			if (difference == 0) { // node has been found

				return null;
			}

			Node parent = null;
			if (difference > 0) {
				parent = forwardCheck(node.right);
				if (parent == null)
					return node;
				return parent;
			}
			parent = forwardCheck(node.left);
			if (parent == null)
				return null;
			return parent;

		}
	}

	// SORTEDMAP STARTS HERE
	private int size;
	private Node root;

	public SortedMap() {
		root = null;
		size = 0;
	}

	/**
	 * Compares this map against another map
	 * 
	 * @param other The map to compare against
	 * @return True if the maps are equal, false otherwise
	 */
	public boolean equals(SortedMap<K, V> other) {
		if (this.root.getData().first.getClass() != other.root.getData().first.getClass())
			return false;
		if (this.root.getData().second.getClass() != other.root.getData().second.getClass())
			return false;
		if (this.size() != other.size())
			return false;
		SortedMapIterator thisItr, otherItr;
		thisItr = this.begin();
		otherItr = other.begin();
		while (thisItr.hasNext()) {
			if (!thisItr.getNode().getData().first.equals(otherItr.getNode().getData().first))
				return false;
			if (!thisItr.getNode().getData().second.equals(otherItr.getNode().getData().second))
				return false;
			thisItr.next();
			otherItr.next();
		}
		return true;

	}

	@Override
	/**
	 * Determines whether a map is empty or not
	 * 
	 * @return True if the map is empty, false otherwise
	 */
	public boolean empty() {
		return size == 0;
	}

	@Override
	/**
	 * Removes a key/value pair from the Map, based on the key
	 * 
	 * @param key The key to remove from the map
	 * @return True if the item was removed, false otherwise
	 */
	public boolean erase(K key) {
		if (find(key) == null)
			return false;
		if (root == null)
			return false;
		root = remove(root, key);
		--size;
		return true;
	}

	// recursive method to remove a node called by erase().
	private Node remove(Node node, K key) {
		if (node == null)
			return node;
		int difference = node.data.first.compareTo(key);
		SortedMapIterator itr = new SortedMapIterator(node);

		if (difference == 0) {
			if (node.left == null) {
				return node.right;
			}
			if (node.right == null) {
				return node.left;
			}

			node.data = new Pair<K, V>(itr.peek().data.first, itr.peek().data.second);
			node.right = remove(node.right, node.data.first);
			return node;

		}
		if (difference > 0) {
			node.left = remove(node.left, key);
			return node;
		}
		node.right = remove(node.right, key);
		return node;
	}

	@Override
	/**
	 * Determines the number of elements within the map
	 * 
	 * @return The number of elements within the map
	 */
	public int size() {
		return size;
	}

	@Override
	/**
	 * Inserts a new key value pair into the map
	 * 
	 * @param key   The key of the value
	 * @param value The value of the item
	 * @return If the item previously existed, returns its position within the map,
	 *         and false. Otherwise, it returns the position of the newly inserted
	 *         item, and true.
	 */
	public Pair<MapIterator, Boolean> insert(K key, V value) {

		SortedMapIterator itr = new SortedMapIterator(null);
		if (root == null) {
			root = new Node(key, value);
			itr.node = root;
			++size;
			return new Pair<MapIterator, Boolean>(itr, true);
		}

		// found the key
		itr = find(key);
		if (itr != null) {

			return new Pair<MapIterator, Boolean>(itr, false);
		}

		// didn't find the key
		itr = placeNode(key, value, root);
		++size;

		return new Pair(itr, false);
	}

	@Override
	/**
	 * Inserts a new key value pair into the map This method calls the other insert
	 * method
	 * 
	 * @param value A Key Value pair to be inserted
	 * @return If the key previously existed, returns its position within the map,
	 *         and false. Otherwise, it returns the position of the newly inserted
	 *         item, and true.
	 */
	public Pair<MapIterator, Boolean> insert(Pair<K, V> value) {
		Pair p = insert(value.first, value.second);
		return p;
	}

	// method recursively finds the spot to enter the key and places it.
	private SortedMapIterator placeNode(K key, V value, Node node) {

		SortedMapIterator itr = null;
		if (node.data.first.compareTo(key) > 0) {
			if (node.left == null) {
				node.left = new Node(key, value);
				return new SortedMapIterator(node.left);
			}
			itr = placeNode(key, value, node.left);
		} else if (node.data.first.compareTo(key) < 0) {

			if (node.right == null) {

				node.right = new Node(key, value);
				return new SortedMapIterator(node.right);
			}
			itr = placeNode(key, value, node.right);
		}

		return itr;

	}

	@Override
	/**
	 * Gets the position of the lower bound, based upon the key
	 * 
	 * @param key The key who's bound is to be found
	 * @return The position of the lower bound of the key
	 * @note The lower bound is the position of the element who's key is greater or
	 *       equal to the key being sought
	 */
	public SortedMapIterator lower_bound(K key) {
		SortedMapIterator itr = begin();
		while (itr.node.data.first.compareTo(key) < 0)
			itr.next();
		return itr;
	}

	@Override
	/**
	 * Gets the position of the upper bound, based upon the key
	 * 
	 * @param key The key who's bound is to be found
	 * @return The position of the upper bound of the key
	 * @note The upper bound is the position of the element who's key is strictly
	 *       greater to the key being sought
	 */
	public SortedMapIterator upper_bound(K key) {
		SortedMapIterator itr = begin();
		while (itr.node.data.first.compareTo(key) <= 0)
			itr.next();
		return itr;
	}

	@Override
	/**
	 * Gets the position of the first element within the map
	 * 
	 * @return The position of the first element
	 */
	public SortedMapIterator begin() {
		if (empty())
			return null;
		Node testNode = root;
		while (testNode.left != null)
			testNode = testNode.left;
		return new SortedMapIterator(testNode);
	}

	@Override
	/**
	 * Removes all entries from the map
	 */
	public void clear() {
		root = null;

	}

	/**
	 * Finds the position of the specified key within the map
	 * 
	 * @param key The key to find
	 * @return The position of the key
	 */
	public SortedMapIterator find(K key) {
		if (empty())
			return null;
		SortedMapIterator itr = new SortedMapIterator(root);
		while (itr.node.data.first.compareTo(key) != 0) {
			if (itr.node.data.first.compareTo(key) < 0) {
				if (itr.node.right != null)
					itr.node = itr.node.right;
				else
					return null;
			}
			if (itr.node.data.first.compareTo(key) > 0) {
				if (itr.node.left != null)
					itr.node = itr.node.left;
				else
					return null;
			}
		}
		return itr;
	}

	@Override
	/**
	 * Gets the position of the last element within the map
	 * 
	 * @return The position of the last element
	 */
	public SortedMapReverseIterator rbegin() {
		if (empty())
			return null;
		Node testNode = root;
		while (testNode.right != null)
			testNode = testNode.right;
		return new SortedMapReverseIterator(testNode);
	}

};
