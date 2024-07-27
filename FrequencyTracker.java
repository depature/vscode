import java.util.Map;

class FrequencyTracker {
    private Map<Integer,Integer> map;
    public FrequencyTracker() {
        map=new HashMap();
    }
    
    public void add(int number) {
        if (map.containsKey(number)) {
            map.put(number, map.get(number)+1);
        }
        else{
            map.put(number, 1);
        }
    }
    
    public void deleteOne(int number) {
        if(map.containsKey(number)){
            if(map.get(number)==1){
                map.remove(number);
            }
            else{
                map.put(number, map.get(number)-1);
            }
        }

    }
    
    public boolean hasFrequency(int frequency) {
        return map.containsKey(frequency);
    }
}