package datastructures;

import java.util.Iterator;

/**
 * An interface for all Maps. Maps contain unique keys, each of which corresponds to a value
 * 
 * @param <K> The type of the key
 * @param <V> The type of the value
 */
public interface Map<K, V> {
	
	public abstract class MapIterator implements Iterator {

	}
	
	public abstract class MapReverseIterator implements Iterator {

	}

	/**
	 * Determines whether a map is empty or not
	 * 
	 * @return True if the map is empty, false otherwise
	 */
	public boolean empty();

	/**
	 * Removes a key/value pair from the Map, based on the key
	 * 
	 * @param key The key to remove from the map
	 * @return True if the item was removed, false otherwise
	 */
	public boolean erase(K key);

	/**
	 * Determines the number of elements within the map
	 * @return The number of elements within the map
	 */
	public int size();

	/**
	 * Inserts a new key value pair into the map
	 * @param key The key of the value
	 * @param value The value of the item
	 * @return If the item previously existed, returns its position within the map, and false. Otherwise, it returns the position of the newly inserted item, and true.
	 */
	public Pair<MapIterator, Boolean> insert(K key, V value);

	/**
	 * Inserts a new key value pair into the map
	 * @param value A Key Value pair to be inserted
	 * @return If the key previously existed, returns its position within the map, and false. Otherwise, it returns the position of the newly inserted item, and true.
	 */
	public Pair<MapIterator, Boolean> insert(Pair<K, V> value);

	/**
	 * Gets the position of the lower bound, based upon the key
	 * @param key The key who's bound is to be found
	 * @return The position of the lower bound of the key
	 * @note The lower bound is the position of the element who's key is greater or equal to the key being sought
	 */
	public MapIterator lower_bound(K key);

	/**
	 * Gets the position of the upper bound, based upon the key
	 * @param key The key who's bound is to be found
	 * @return The position of the upper bound of the key
	 * @note The upper bound is the position of the element who's key is strictly greater to the key being sought
	 */
	public MapIterator upper_bound(K key);

	/**
	 * Gets the position of the first element within the map
	 * @return The position of the first element
	 */
	public MapIterator begin();

	/**
	 * Gets the position of the last element within the map
	 * @return The position of the last element
	 */
	public MapReverseIterator rbegin();

	/**
	 * Removes all entries from the map
	 */
	public void clear();

	/**
	 * Finds the position of the specified key within the map
	 * @param key The key to find
	 * @return The position of the key
	 */
	public MapIterator find(K key);

};