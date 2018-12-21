package cn.neocer.english;

public class Stringcut
{
    public static String GetMidString(String body,String str1,String str2)
    {
       int start = body.indexOf(str1)+str1.length();
       int end = body.indexOf(str2,start);
       if(start<str1.length() || end <0)
           return "";
       return body.substring(start,end);

    }
    public static String[] GetMidString2(String source,String LString,String RString)
    {
        String returnString[] = new String[0];
        String tempString = new String(source);
        int start = tempString.indexOf(LString)+LString.length();
        if(start<LString.length())
            return returnString;
        int end = tempString.indexOf(RString,start);
        while(start>=LString.length() && end>=0)
        {
            String temp = tempString.substring(start,end);
            tempString = tempString.substring(end + RString.length());
            start = tempString.indexOf(LString)+LString.length();
            String TmpString[] = new String[returnString.length+1];
            for(int i = 0 ; i < returnString.length; i ++)
            {
                TmpString[i] = returnString[i];
            }
            returnString = TmpString;
            returnString[returnString.length-1] = temp;

            if(start<LString.length())
               break;
            end = tempString.indexOf(RString,start);

        }
        return returnString;
    }

}
