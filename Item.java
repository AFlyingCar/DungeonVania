/*
 * Tyler Robbins
 * Item
 * 5/8/15
 * Class which dictates how items work.
 */

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.util.Map;
import java.util.HashMap;
import javax.management.openmbean.KeyAlreadyExistsException;

public class Item{
	private Map<String,Object> attributes;
	private final String NAME;

	/*
	Creates a key-value mapping of dynamic attributes.
	PostCondition: attributes has been instantiated as a HashMap, NAME has been instantiated as a String
	*/
	public Item(String newName){
		attributes = new HashMap<String,Object>();
		NAME = newName;
	}

	/*
	Adds an attribute to the current item.
	PreCondition: attributes is not null
	PostCondition: attributes has a new key-value pair
	*/
	public void addAttribute(String key, Object value){
		if(!attributes.containsKey(key))
			attributes.put(key,value);
		else
			throw new KeyAlreadyExistsException("Key " + key + " already exists.");
	}

	public Map<String,Object> getAllAttributes(){ return attributes; }

	/*
	Returns the attribute associated with the String key.
	PreCondition: attributes is not null
	*/
	public Object getItemAttribute(String key){
		return attributes.get(key);
	}

	public void setItemAttribute(String key,Object value){
		if(attributes.containsKey(key))
			attributes.put(key,value);
		else
			throw new IndexOutOfBoundsException("Key " + key + " does not exist.");
	}

	/*
	Returns true if and only if the key exists in attributes.
	PreCondition: attributes is not null
	*/
	public boolean doesContainAttribute(String key){
		return attributes.containsKey(key);
	}

	/*
	Returns the name of the item.
	PreCondition: NAME has been initialized
	*/
	public String getName(){ return NAME; }

	public String toString(){
		String repr = NAME + "\n";
		for(Map.Entry<String,Object> e : attributes.entrySet()){
			repr += e.getKey();
			repr += ":\t";
			repr += e.getValue();
			repr += "\n";
		}

		return repr;
	}
}
