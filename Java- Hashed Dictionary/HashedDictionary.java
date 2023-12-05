//
// Name: Thompson, Jacob
// Project: 3
// Due: 3/24/2023
// Course: cs-2400-03-sp23
//
// Description:
// In this project we needed to create A hashed Dictionary and an application which utilizes it.
// The Hashed Dictionary needs to incorporate the methods add, getValue, and getKeyIterator.
// It also needs to include a method to get the amount of collisions that happened in a Dictionary.
//
// The aplication's goal is to read in a text document and utilize a Dictionary to store and count the collisions which happen.
// It also must print out the word count of each word. 
//


import java.util.Iterator;
import java.util.NoSuchElementException;
 /**
 A class that implements the ADT dictionary by using hashing and
 linear probing to resolve collisions.
 The dictionary is unsorted and has distinct search keys.
 Search keys and associated values are not null.
 */
	public class HashedDictionary<K, V> implements DictionaryInterface<K, V> {
		
		// The dictionary:
		private int numberOfEntries;
		private static final int DEFAULT_CAPACITY = 5; // Must be prime
		private static final int MAX_CAPACITY = 10000;

		// The hash table:
		private Entry<K, V>[] hashTable;
		private int tableSize; // Must be prime
		private int collisions;
		private static final int MAX_SIZE = 2 * MAX_CAPACITY;
		protected final Entry<K, V> AVAILABLE = new Entry<>(null, null);

		
		public HashedDictionary() {
			this(DEFAULT_CAPACITY); // Call next constructor
		} // end default constructor

		
		public HashedDictionary(int initialCapacity) {
			initialCapacity = checkCapacity(initialCapacity);
			numberOfEntries = 0; // Dictionary is empty
			collisions = 0;

			tableSize = (initialCapacity);

			if (tableSize > MAX_SIZE) {
				throw new IllegalArgumentException("dictionary has reached its Max_Size.");
			}

			// The cast is safe because the new array contains null entries
			@SuppressWarnings("unchecked")
			Entry<K, V>[] temp = (Entry<K, V>[]) new Entry[tableSize];
			hashTable = temp;

		} // end constructor

		
		
		
		public V getValue(K key) {
			checkIntegrity();

			V result = null;

			int index = getHashIndex(key);
			index = probe(index, key);

			if ((hashTable[index] != null) && (hashTable[index] != AVAILABLE))
				result = hashTable[index].getValue(); // Key found; get value
			
			// Else key not found; return null
			return result;
		} // end getValue

		
		
		
		public V add(K key, V value) {
			checkIntegrity();

			if ((key == null) || (value == null)) {
				throw new IllegalArgumentException("Cannot add null to this dictionary.");
			}

			else {
				V oldValue = null;

				int index = getHashIndex(key);
				int comp1 = index;
				index = probe(index, key);
				if (comp1 != index && hashTable[index] == null)
					collisions++;

				
				if (hashTable[index] == null) {

					hashTable[index] = new Entry<>(key, value);
					numberOfEntries++;

					oldValue = null;

				} else {

					oldValue = hashTable[index].getValue();
					hashTable[index].setValue(value);

				}

				return oldValue;
			} // end if
		}

		
		
		
		
		public Iterator<K> getKeyIterator() {
			return new KeyIterator();
		}

		
		public int getCollisionCount() {
			return collisions;
		}

		
		//UNSUPPORTED OPERATIONS!
		
		public V remove(K key) {
			throw new UnsupportedOperationException("remove() is not supported " + "by this Dictonary");
		}

		public boolean contains(K key) {
			throw new UnsupportedOperationException("contains() is not supported " + "by this Dictonary");
		}

		public Iterator<V> getValueIterator() {
			throw new UnsupportedOperationException("getValueIterator() is not supported " + "by this Dictonary");
		}

		public boolean isEmpty() {
			throw new UnsupportedOperationException("isEmpty() is not supported " + "by this Dictonary");
		}

		public int getSize() {
			throw new UnsupportedOperationException("getSize() is not supported " + "by this Dictonary");
		}

		public void clear() {
			throw new UnsupportedOperationException("clear() is not supported " + "by this Dictonary");
		}

		//
		
		
		
		
		public void checkIntegrity() {
			int count = 0;
			for (Entry<K, V> entry : hashTable) {
				if (entry != null) {
					count++;
				}
			}
			if (count != numberOfEntries) {
				throw new IllegalStateException(
						"Number of entries does not match actual number of entries in hash table");
			}
		}

						
		private int checkCapacity(int capacity) {
			if (capacity <= 0 || capacity > MAX_CAPACITY) {
				throw new IllegalArgumentException("Capacity must be between 1 and " + MAX_CAPACITY);
			}
			return capacity;
		}

		
		
		
		private class KeyIterator implements Iterator<K> {
			private int currentIndex; // Current position in hash table
			private int NumberLeft; // Number of entries left in iteration

			private KeyIterator() {
				currentIndex = 0;
				NumberLeft = numberOfEntries;
			} // end default constructor

			public boolean hasNext() {
				return NumberLeft > 0;
			} // end hasNext

			public K next() {
				K result = null;
				if (hasNext()) {

					// Skip table locations that do not contain a current entry
					while ((hashTable[currentIndex] == null) || hashTable[currentIndex] == AVAILABLE) {

						currentIndex++;
					} // end while

					result = hashTable[currentIndex].getKey();
					NumberLeft--;
					currentIndex++;
				} else
					throw new NoSuchElementException();

				return result;
			} // end next

			public void remove() {
				throw new UnsupportedOperationException();
			} // end remove
		} // end KeyIterator

		
		
		
		public int getHashIndex(K key) {

			int hashIndex = key.hashCode() % hashTable.length;
			
			if (hashIndex < 0)
				hashIndex = hashIndex + hashTable.length;

			return hashIndex;
		} // end getHashIndex

		
		
		
		private int probe(int index, K key) {
			boolean found = false;
			int availableStateIndex = -1; // Index of first element in available state
			while (!found && (hashTable[index] != null)) {
				if (hashTable[index] != AVAILABLE) {
					if (key.equals(hashTable[index].getKey()))
						found = true; // Key found
					
					else // Follow probe sequence
						index = (index + 1) % hashTable.length; // Linear probing
					
				} else // Element in available state; skip it, but mark the first one encountered
				{
					// Save index of first element in available state
					if (availableStateIndex == -1)
						availableStateIndex = index;
					index = (index + 1) % hashTable.length; // Linear probing
					
				} // end if
			} // end while
				// Assertion: Either key or null is found at hashTable[index]
			if (found || (availableStateIndex == -1))
				return index; // Index of either key or null
			else
				return availableStateIndex; // Index of an available element
		} // end linearProbe

		
		
		
		protected final class Entry<K2, V2> {
			private K2 key;
			private V2 value;

			private Entry(K2 searchKey, V2 dataValue) {
				key = searchKey;
				value = dataValue;

			} // end constructor

			private K2 getKey() {
				return key;
			} // end getKey

			private V2 getValue() {
				return value;
			}

			private void setValue(V2 newValue) {
				value = newValue;
			} // end setValue

		} // end Entry

	} // end HashedDictionary