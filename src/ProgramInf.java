public class ProgramInf implements Comparable<ProgramInf>{
    private String channel;
    private BroadcastsTime time;
    private String name;
    public ProgramInf(String channel,BroadcastsTime time, String name) {
        this.channel = channel;
        this.time = time;
        this.name = name;
    }

    public BroadcastsTime getTime() {
        return time;
    }

    public String getChannel() {
        return channel;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ProgramSchedule{" +
                "channel='" + channel + '\'' +
                ", time='" + time + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    @Override
    public int compareTo(ProgramInf other) {
        return this.channel.compareTo(other.channel);
    }

}
