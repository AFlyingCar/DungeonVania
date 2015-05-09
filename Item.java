/*
 * Tyler Robbins
 * Item
 * 5/8/15
 * Class which dictates how items work.
 */

public class Item{
	private Map<String,Object> attributes;
	private final String NAME;

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
		attributes.put(key,value);
	}

	/*
	Returns the attribute associated with the String key.
	PreCondition: attributes is not null
	*/
	public Object getItemAttribute(String key){
		return attributes.get(key);
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
		}

		return repr;
	}
}