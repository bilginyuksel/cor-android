public class Event {
    private String event;
    private Object[] params;

    public Event(String event, Object ...params){
        this.params = params;
        this.event = event;
    }

    public Object[] getParams() {
        return params;
    }

    public String getEvent() {
        return event;
    }
}
