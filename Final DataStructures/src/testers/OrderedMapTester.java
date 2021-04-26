
package testers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import datastructures.Pair;
import datastructures.SortedMap;
import datastructures.SortedMap.SortedMapIterator;
import datastructures.SortedMap.SortedMapReverseIterator;


class OrderedMapTester {
	SortedMap<String, Integer> sm;
	SortedMapIterator itr;
	SortedMapReverseIterator ritr;
	SortedMap<String, Integer> tm;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		sm = new SortedMap<String, Integer>();
		tm = new SortedMap<String, Integer>();
	}


	@Test
	void empty() {
		assertTrue(sm.empty());
		sm.insert("a",1);
		assertFalse(sm.empty());
	}
	
	@Test
	void begin() {
		sm.insert("b",3);
		sm.insert("a",1);
		itr=sm.begin();
		assertEquals(itr.getNode().getData().first,"a");
	}
	
	@Test
	void rbegin() {
		sm.insert("b",3);
		sm.insert("a",1);
		ritr=sm.rbegin();
		assertEquals(ritr.getNode().getData().first,"b");
	}
	
	@Test
	void insert() {
		sm.insert("f",3);
		sm.insert("c",1);
		sm.insert("b",5);
		sm.insert("b",5);
		sm.insert("a",5);
		sm.insert("d",5);
		sm.insert("e",5);
		itr=sm.begin();
		assertEquals(itr.getNode().getData().first,"a");
		itr.next();
		assertEquals(itr.getNode().getData().first,"b");
		itr.next();
		assertEquals(itr.getNode().getData().first,"c");
		itr.next();
		assertEquals(itr.getNode().getData().first,"d");
		itr.next();
		assertEquals(itr.getNode().getData().first,"e");
		itr.next();
		assertEquals(itr.getNode().getData().first,"f");
	}
	
	@Test
	void find() {
		sm.insert("a",1);
		itr=sm.find("a");
		assertEquals(itr.getNode().getData().first,"a");
		itr=sm.find("b");
		assertNull(itr);
	}
	
	@Test
	void erase() {
		sm.insert("f",3);
		sm.insert("c",1);
		sm.insert("b",5);
		sm.insert("b",5);
		sm.insert("a",5);
		sm.insert("d",5);
		sm.insert("e",5);
		sm.erase("a");
		sm.erase("c");
		itr=sm.begin();
		assertEquals(itr.getNode().getData().first,"b");
		itr.next();
		assertEquals(itr.getNode().getData().first,"d");
		itr.next();
		assertEquals(itr.getNode().getData().first,"e");
		itr.next();
		assertEquals(itr.getNode().getData().first,"f");
	}
	
	@Test
	void size() {
		assertEquals(sm.size(),0);
		sm.insert("f",3);
		sm.insert("c",1);
		sm.insert("b",5);
		sm.insert("b",5);
		sm.insert("a",5);
		sm.insert("d",5);
		sm.insert("e",5);
		assertEquals(sm.size(),6);
		sm.erase("a");
		sm.erase("c");
		assertEquals(sm.size(),4);
	}
	
	@Test
	void upper() {
		sm.insert("f",3);
		sm.insert("c",1);
		sm.insert("b",5);
		sm.insert("a",5);
		sm.insert("d",5);
		sm.insert("e",5);
		itr=sm.upper_bound("d");
		assertEquals(itr.getNode().getData().first,"e");
	}
	
	@Test
	void lower() {
		sm.insert("f",3);
		sm.insert("c",1);
		sm.insert("b",5);
		sm.insert("a",5);
		sm.insert("d",5);
		sm.insert("e",5);
		itr=sm.lower_bound("d");
		assertEquals(itr.getNode().getData().first,"d");
		sm.erase("d");
		itr=sm.lower_bound("d");
		assertEquals(itr.getNode().getData().first,"e");
	}
	
	@Test 
	void next() {
		sm.insert("f",3);
		sm.insert("c",1);
		sm.insert("b",5);
		sm.insert("a",5);
		sm.insert("d",5);
		sm.insert("e",5);
		itr=sm.begin();
		Pair p = itr.next();
		assertEquals(p.first,"b");
		p = itr.next();
		assertEquals(p.first,"c");
		p = itr.next();
		assertEquals(p.first,"d");
		p = itr.next();
		assertEquals(p.first,"e");
		
	}
	
	@Test 
	void rnext() {
		sm.insert("f",3);
		sm.insert("c",1);
		sm.insert("b",5);
		sm.insert("a",5);
		sm.insert("d",5);
		sm.insert("e",5);
		ritr=sm.rbegin();
		Pair p = ritr.next();
		assertEquals(p.first,"e");
		p = ritr.next();
		assertEquals(p.first,"d");
		p = ritr.next();
		assertEquals(p.first,"c");
		p = ritr.next();
		assertEquals(p.first,"b");
	}

	@Test
	void equals() {
		sm.insert("f",3);
		sm.insert("c",1);
		sm.insert("b",5);
		sm.insert("a",5);
		sm.insert("d",5);
		sm.insert("e",5);
		 
		tm.insert("e",5);
		tm.insert("c",1);
		tm.insert("b",5);
		tm.insert("a",5);
		tm.insert("d",5);
		tm.insert("f",3);
		assertTrue(sm.equals(tm));
	}
		
}
