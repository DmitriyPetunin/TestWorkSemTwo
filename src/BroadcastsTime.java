public class BroadcastsTime implements Comparable<BroadcastsTime>{
    private byte hour;
    private byte minutes;
    private int second;

    public BroadcastsTime(String times) {
        String[] time = times.split(":");
        this.hour = Byte.parseByte(time[0]);
        this.minutes = Byte.parseByte(time[1]);
        second = hour*3600 + minutes*60;
    }

    public int getSecond() {
        return second;
    }

    public byte getHour() {
        return hour;
    }

    public byte getMinutes() {
        return minutes;
    }

    boolean after(BroadcastsTime t) {
        return (getSecond() - t.getSecond()) > 0;
    }
    boolean befor(BroadcastsTime t) {
        return (getSecond() - t.getSecond()) < 0;
    }
    boolean between(BroadcastsTime t1, BroadcastsTime t2) {
        return (t2.getSecond() - t1.getSecond()) > getSecond();
    }
    @Override
    public int compareTo(BroadcastsTime o) {
        return getSecond() - o.getSecond();
    }

    @Override
    public String toString() {
        return "BroadcastsTime{" +
                "hour=" + hour +
                ", minutes=" + minutes +
                '}';
    }
}
