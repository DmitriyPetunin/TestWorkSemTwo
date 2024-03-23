import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("/Users/dmitriypetunin/IdeaProjects/TestWorkSemTwo/schedule.txt"));
        List<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.nextLine());
        }
        s.close();

        Map<BroadcastsTime, List<ProgramInf>> listMap = new LinkedHashMap<>();
        List<ProgramInf> scheduleList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).charAt(0) == '#') {
                String channel = list.get(i);
                i++;
                while (list.get(i).charAt(0) != '#'){
                    BroadcastsTime broadcastsTime = new BroadcastsTime(list.get(i));
                    ProgramInf program = new ProgramInf(channel, broadcastsTime, list.get(i+1));
                    scheduleList.add(program);
                    if (listMap.containsKey(broadcastsTime)){
                        listMap.get(broadcastsTime).add(program);
                    } else listMap.put(broadcastsTime, new ArrayList<>(List.of(program)));
                    i = i + 2;
                    if (i >= list.size()){
                        break;
                    }
                }
                i = i - 1;
            }
        }
        System.out.println(scheduleList);
        System.out.println(listMap);
        Collections.sort(scheduleList);
        System.out.println(scheduleList);

        System.out.println(searchViaName("Сто к одному", scheduleList));
        System.out.println(searchViaChannelNow("#Россия 1",scheduleList));
        System.out.println(searchViaNow(new BroadcastsTime("10:10"),scheduleList));
        System.out.println(searchViaBetweenTime(new BroadcastsTime("08:00"), new BroadcastsTime("09:25"), scheduleList));

    }
//
    public static  List<ProgramInf> searchViaName(String str, List<ProgramInf> programs){
        List<ProgramInf> searchPrograms = new ArrayList<>();
        for(ProgramInf program: programs){
            if (program.getName().startsWith(str)){
                searchPrograms.add(program);
            }
        }
        return searchPrograms;
    }


    public static  List<ProgramInf> searchViaChannelNow(String str, List<ProgramInf> programs){

        List<ProgramInf> sendPrograms = new ArrayList<>();
        Date date = new Date();
        int nowSeconds = date.getHours() + date.getMinutes();
        for (ProgramInf program: programs){
            if (program.getTime().getSecond() == nowSeconds && program.getChannel().startsWith(str)){
                sendPrograms.add(program);
            }
        }
        return sendPrograms;
    }
    public static  List<ProgramInf> searchViaNow(BroadcastsTime broadcastsTime, List<ProgramInf> programs){

        List<ProgramInf>  searchProgramms = new ArrayList<>();
        int nowSecunds = broadcastsTime.getSecond();
        for(ProgramInf program: programs){
            if (program.getTime().getSecond() == nowSecunds){
                searchProgramms.add(program);

            }
        }
        return searchProgramms;
    }
    public static  List<ProgramInf> searchViaBetweenTime(BroadcastsTime t1, BroadcastsTime t2, List<ProgramInf> programs){
        List<ProgramInf>  searchPrograms = new ArrayList<>();
        for(ProgramInf program: programs){
            if (program.getTime().between(t1, t2)){
                searchPrograms.add(program);
            }
        }
        return searchPrograms;
    }
}