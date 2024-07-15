import java.io.*;
import java.util.HashMap;

public class App 
{
    public static void main(String[] args) throws Exception 
    {
        HashMap<Integer, Patient> map = new HashMap<>();
        //HashMap<Integer, Patient> iMapCopy = new HashMap<>();
        //HashMap<Integer, Patient> qMapCopy = new HashMap<>();
        //HashMap<Integer, Patient> sMapCopy = new HashMap<>();
        int count = 0;
        int count2 = 0;
/*
        String vaxFile1 = "2020VAERSVAX.csv";
        String dataFile1 = "2020VAERSDATA.csv";
        String symptomFile1 = "2020VAERSSYMPTOMS.csv";

        readFiles(vaxFile1, dataFile1, symptomFile1, map);

        System.out.println("Done reading 2020 File");

        String vaxFile2 = "2021VAERSVAX.csv";
        String dataFile2 = "2021VAERSDATA.csv";
        String symptomFile2 = "2021VAERSSYMPTOMS.csv";

        readFiles(vaxFile2, dataFile2, symptomFile2, map);

        System.out.println("Done reading 2021 File");

        String vaxFile3 = "2022VAERSVAX.csv";
        String dataFile3 = "2022VAERSDATA.csv";
        String symptomFile3 = "2022VAERSSYMPTOMS.csv";

        readFiles(vaxFile3, dataFile3, symptomFile3, map);

        System.out.println("Done reading 2022 File");

        String vaxFile4 = "2023VAERSVAX.csv";
        String dataFile4 = "2023VAERSDATA.csv";
        String symptomFile4 = "2023VAERSSYMPTOMS.csv";

        readFiles(vaxFile4, dataFile4, symptomFile4, map);

        System.out.println("Done reading 2023 File");
*/
        String vaxFile5 = "NonDomesticVAERSVAX.csv";
        String dataFile5 = "NonDomesticVAERSDATA.csv";
        String symptomFile5 = "NonDomesticVAERSSYMPTOMS.csv";

        readFiles(vaxFile5, dataFile5, symptomFile5, map);

        System.out.println("Done reading Non Domestic File");

        //iMapCopy = map;
        //insertionSortFile(iMapCopy);

        //qMapCopy = map;
        //quickSortFile(qMapCopy);

        //sMapCopy = map;
        //selectionSortFile(sMapCopy);

        PrintWriter out = new PrintWriter(new File("VAERS_COVID_DataAugust2023.csv"));

        out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", "VAERS_ID", "RECVDATE", "STATE", "AGE_YRS", "CAGE_YR", "CAGE_MO", "SEX", "RPT_DATE", "SYMPTOM_TEXT", "DIED", "DATEDIED", "L_THREAT", "ER_VISIT", "HOSPITAL", "HOSPDAYS", "X_STAY", "DISABLE", "RECOVD", "VAX_DATE", "ONSET_DATE", "NUMDAYS", "LAB_DATA", "V_ADMINBY", "V_FUNDBY", "OTHER_MEDS", "CUR_ILL", "HISTORY", "PRIOR_VAX", "SPLTTYPE", "FORM_VERS", "TODAYS_DATE", "BIRTH_DEFECT", "OFC_VISIT", "ER_ED_VISIT", "ALLERGIES", "VAX_TYPE", "VAX_MANU", "VAX_LOT", "VAX_DOSE_SERIES", "VAX_ROUTE", "VAX_SITE", "VAX_NAME", "SYMPTOM1", "SYMPTOMVERSION1", "SYMPTOM2", "SYMPTOMVERSION2", "SYMPTOM3", "SYMPTOMVERSION3", "SYMPTOM4", "SYMPTOMVERSION4", "SYMPTOM5", "SYMPTOMVERSION5");

        for(int id : map.keySet())
        {
            count++;
            for(int i = 0; i < map.get(id).getVaccines().size(); i++)
            {
                count2++;
                out.printf("\n%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,", map.get(id).getVaersID(), map.get(id).getDate(), map.get(id).getState(), map.get(id).getAgeYrs(), map.get(id).getCageYr(), map.get(id).getCageMo(), map.get(id).getSex(), map.get(id).getRptDate(), map.get(id).getSymptomText(), map.get(id).getDied(), map.get(id).getDateDied(), map.get(id).getLThreat(), map.get(id).getErVisit(), map.get(id).getHospital(), map.get(id).getHospdays(), map.get(id).getXStay(), map.get(id).getDisable(), map.get(id).getRecovd(), map.get(id).getVaxDate(), map.get(id).getOnsetDate(), map.get(id).getNumDays(), map.get(id).getLabData(), map.get(id).getVAdminBy(), map.get(id).getVFundBy(), map.get(id).getOtherMeds(), map.get(id).getCurIll(), map.get(id).getHistory(), map.get(id).getPriorVax(), map.get(id).getSpltType(), map.get(id).getFormVers(), map.get(id).getTodaysDate(), map.get(id).getBirthDefect(), map.get(id).getOfcVisit(), map.get(id).getErEDVisit(), map.get(id).getAllergies(), map.get(id).getVaccine(i).getVaxType(), map.get(id).getVaccine(i).getVaxManu(), map.get(id).getVaccine(i).getVaxLot(), map.get(id).getVaccine(i).getVaxDoseSeries(), map.get(id).getVaccine(i).getVaxRoute(), map.get(id).getVaccine(i).getVaxSite(), map.get(id).getVaccine(i).getVaxName());
            
                for(int j = 0; j < map.get(id).getSymptoms().size(); j++)
                {
                    out.printf("%s,%s,", map.get(id).getSymptom(j).getSymptom(), map.get(id).getSymptom(j).getSymptomVersion());
                }
            }
        }

        System.out.println(count);
        System.out.println(count2);

        out.close();

        //String sortedFile = "VAERS_COVID_DataAugust2023.csv";

        //groupFile(sortedFile);
    }

    public static void readFiles(String vaxFile, String dataFile, String symptomFile, HashMap<Integer, Patient> myMap)
    {
        BufferedReader vaxFileReader = null;
        String vaxFileLine = "";

        BufferedReader dataFileReader = null;
        String dataFileLine = "";

        BufferedReader symptomFileReader = null;
        String symptomFileLine = "";

        try
        {
            vaxFileReader = new BufferedReader(new FileReader(vaxFile));
            dataFileReader = new BufferedReader(new FileReader(dataFile));
            symptomFileReader = new BufferedReader(new FileReader(symptomFile));

            vaxFileLine = vaxFileReader.readLine();
            vaxFileLine = vaxFileReader.readLine();

            while(vaxFileLine != null)
            {
                String[] vaxRow = vaxFileLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                if(vaxRow[1].contains("COVID19"))
                {
                    if(myMap.containsKey(Integer.parseInt(vaxRow[0])))
                    {
                        Patient curPatient = myMap.get(Integer.parseInt(vaxRow[0]));

                        Vaccine newVaccine = new Vaccine();
                        myMap.get(curPatient.getVaersID()).addVaccine(newVaccine);

                        myMap.get(curPatient.getVaersID()).getVaccine(myMap.get(curPatient.getVaersID()).getVaxCount() - 1).setVaxType(vaxRow[1]);
                        myMap.get(curPatient.getVaersID()).getVaccine(myMap.get(curPatient.getVaersID()).getVaxCount() - 1).setVaxManu(vaxRow[2]);
                        myMap.get(curPatient.getVaersID()).getVaccine(myMap.get(curPatient.getVaersID()).getVaxCount() - 1).setVaxLot(vaxRow[3]);
                        myMap.get(curPatient.getVaersID()).getVaccine(myMap.get(curPatient.getVaersID()).getVaxCount() - 1).setVaxDoseSeries(vaxRow[4]);
                        myMap.get(curPatient.getVaersID()).getVaccine(myMap.get(curPatient.getVaersID()).getVaxCount() - 1).setVaxRoute(vaxRow[5]);
                        myMap.get(curPatient.getVaersID()).getVaccine(myMap.get(curPatient.getVaersID()).getVaxCount() - 1).setVaxSite(vaxRow[6]);
                        myMap.get(curPatient.getVaersID()).getVaccine(myMap.get(curPatient.getVaersID()).getVaxCount() - 1).setVaxName(vaxRow[7]);
                    }

                    else
                    {
                        Patient newPatient = new Patient(Integer.parseInt(vaxRow[0]));
                        myMap.put(newPatient.getVaersID(), newPatient);

                        Vaccine newVaccine = new Vaccine();
                        myMap.get(newPatient.getVaersID()).addVaccine(newVaccine);

                        myMap.get(newPatient.getVaersID()).getVaccine(myMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxType(vaxRow[1]);
                        myMap.get(newPatient.getVaersID()).getVaccine(myMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxManu(vaxRow[2]);
                        myMap.get(newPatient.getVaersID()).getVaccine(myMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxLot(vaxRow[3]);
                        myMap.get(newPatient.getVaersID()).getVaccine(myMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxDoseSeries(vaxRow[4]);
                        myMap.get(newPatient.getVaersID()).getVaccine(myMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxRoute(vaxRow[5]);
                        myMap.get(newPatient.getVaersID()).getVaccine(myMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxSite(vaxRow[6]);
                        myMap.get(newPatient.getVaersID()).getVaccine(myMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxName(vaxRow[7]);
                    }
                }

                vaxFileLine = vaxFileReader.readLine();
            }

            dataFileLine = dataFileReader.readLine();
            dataFileLine = dataFileReader.readLine();

            while(dataFileLine != null)
            {
                String[] dataRow = dataFileLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        
                if(myMap.containsKey(Integer.parseInt(dataRow[0])))
                {
                    int curKey = Integer.parseInt(dataRow[0]);

                    myMap.get(curKey).setDate(dataRow[1]);
                    myMap.get(curKey).setState(dataRow[2]);

                    if(dataRow[3].equals(""))
                    {
                        myMap.get(curKey).setAgeYrs(-1);
                    }

                    else
                    {
                        myMap.get(curKey).setAgeYrs(Double.parseDouble(dataRow[3]));
                    }

                    if(dataRow[4].equals(""))
                    {
                        myMap.get(curKey).setCageYr(-1);
                    }

                    else
                    {
                        myMap.get(curKey).setCageYr(Integer.parseInt(dataRow[4]));
                    }

                    myMap.get(curKey).setCageMo(dataRow[5]);
                    myMap.get(curKey).setSex(dataRow[6]);
                    myMap.get(curKey).setRptDate(dataRow[7]);
                    myMap.get(curKey).setSymptomText(dataRow[8]);
                    myMap.get(curKey).setDied(dataRow[9]);
                    myMap.get(curKey).setDateDied(dataRow[10]);
                    myMap.get(curKey).setLThreat(dataRow[11]);
                    myMap.get(curKey).setErVisit(dataRow[12]);
                    myMap.get(curKey).setHospital(dataRow[13]);
                    myMap.get(curKey).setHospdays(dataRow[14]);
                    myMap.get(curKey).setXStay(dataRow[15]);
                    myMap.get(curKey).setDisable(dataRow[16]);
                    myMap.get(curKey).setRecovd(dataRow[17]);
                    myMap.get(curKey).setVaxDate(dataRow[18]);
                    myMap.get(curKey).setDate(dataRow[19]);
                    myMap.get(curKey).setOnsetDate(dataRow[20]);
                    myMap.get(curKey).setNumDays(dataRow[21]);
                    myMap.get(curKey).setLabData(dataRow[22]);
                    myMap.get(curKey).setVAdminBy(dataRow[23]);
                    myMap.get(curKey).setVFundBy(dataRow[24]);
                    myMap.get(curKey).setOtherMeds(dataRow[25]);
                    myMap.get(curKey).setCurIll(dataRow[26]);
                    myMap.get(curKey).setHistory(dataRow[27]);
                    myMap.get(curKey).setSpltType(dataRow[28]);
                    myMap.get(curKey).setFormVers(dataRow[29]);

                    if(dataRow.length == 30)
                    {
                        myMap.get(curKey).setTodaysDate("");
                        myMap.get(curKey).setBirthDefect("");
                        myMap.get(curKey).setOfcVisit("");
                        myMap.get(curKey).setErEDVisit("");
                        myMap.get(curKey).setAllergies("");
                    }

                    else if(dataRow.length == 31)
                    {
                        myMap.get(curKey).setTodaysDate(dataRow[30]);
                        myMap.get(curKey).setBirthDefect("");
                        myMap.get(curKey).setOfcVisit("");
                        myMap.get(curKey).setErEDVisit("");
                        myMap.get(curKey).setAllergies("");
                    }

                    else if(dataRow.length == 32)
                    {
                        myMap.get(curKey).setTodaysDate(dataRow[30]);
                        myMap.get(curKey).setBirthDefect(dataRow[31]);
                        myMap.get(curKey).setOfcVisit("");
                        myMap.get(curKey).setErEDVisit("");
                        myMap.get(curKey).setAllergies("");
                    }

                    else if(dataRow.length == 33)
                    {
                        myMap.get(curKey).setTodaysDate(dataRow[30]);
                        myMap.get(curKey).setBirthDefect(dataRow[31]);
                        myMap.get(curKey).setOfcVisit(dataRow[32]);
                        myMap.get(curKey).setErEDVisit("");
                        myMap.get(curKey).setAllergies("");
                    }

                    else if(dataRow.length == 34)
                    {
                        myMap.get(curKey).setTodaysDate(dataRow[30]);
                        myMap.get(curKey).setBirthDefect(dataRow[31]);
                        myMap.get(curKey).setOfcVisit(dataRow[32]);
                        myMap.get(curKey).setErEDVisit(dataRow[33]);
                        myMap.get(curKey).setAllergies("");
                    }

                    else
                    {
                        myMap.get(curKey).setTodaysDate(dataRow[30]);
                        myMap.get(curKey).setBirthDefect(dataRow[31]);
                        myMap.get(curKey).setOfcVisit(dataRow[32]);
                        myMap.get(curKey).setErEDVisit(dataRow[33]);
                        myMap.get(curKey).setAllergies(dataRow[34]);
                    }
                }

                dataFileLine = dataFileReader.readLine();
            }

            symptomFileLine = symptomFileReader.readLine();
            symptomFileLine = symptomFileReader.readLine();

            while(symptomFileLine != null)
            {
                String[] symptomRow = symptomFileLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        
                if(myMap.containsKey(Integer.parseInt(symptomRow[0])))
                {
                    for(int i = 1; i < symptomRow.length - 1; i = i + 2)
                    {
                        myMap.get(Integer.parseInt(symptomRow[0])).addSymptom(symptomRow[i], symptomRow[i + 1]);
                    }
                }

                symptomFileLine = symptomFileReader.readLine();
            }

            vaxFileReader.close();
            dataFileReader.close();
            symptomFileReader.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }  
/*
    public static void quickSort(int myArray[], int low, int high)
    {
        if (low < high)
        {
            Random rand = new Random();

            int tempPivot = rand.nextInt(high - low) + low;
            
            int temp = myArray[tempPivot]; 
            myArray[tempPivot] = myArray[high];
            myArray[high] = temp;

            int pivot = myArray[high];
        
            int i = (low-1); 

            for (int j = low; j < high; j++)
            {
                if (myArray[j] < pivot)
                {
                    i++;
    
                    int temp2 = myArray[i];
                    myArray[i] = myArray[j];
                    myArray[j] = temp2;
                }
            }
    
            int temp3 = myArray[i + 1];
            myArray[i + 1] = myArray[high];
            myArray[high] = temp3;
    
            int pi = i + 1;

            quickSort(myArray, low, pi - 1);
            quickSort(myArray, pi + 1, high);
        }
    }

    public static void quickSortFile(HashMap<Integer, Patient> myQMap)
    {
        try
        {
            int[] idArray = new int[myQMap.size()];
            int count = 0;

            for(int id : myQMap.keySet())
            {
                idArray[count] = myQMap.get(id).getVaersID();
                count++;
            }

            int idArrayLen = idArray.length;
            int low = 0;
            int high = idArrayLen - 1;

            quickSort(idArray, low, high);

            PrintWriter out = new PrintWriter(new File("Quick_Sort_VAERS_COVID_DataAugust2023.csv"));

            out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s\n", "VAERS_ID", "AGE_YRS", "SEX", "VAX_NAME", "RPT_DATE", "SYMPTOM", "DIED", "DATEDIED", "SYMPTOM_TEXT");

            for(int i = 0; i < idArray.length; i++)
            {
                for(int j = 0; j < myQMap.get(idArray[i]).getSymptoms().size(); j++)
                {
                    out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s", myQMap.get(idArray[i]).getVaersID(), myQMap.get(idArray[i]).getAgeYrs(), myQMap.get(idArray[i]).getSex(), myQMap.get(idArray[i]).getVaxName(), myQMap.get(idArray[i]).getRptDate(), myQMap.get(idArray[i]).getSymptom(j).getSymptom(), myQMap.get(idArray[i]).getDied(), myQMap.get(idArray[i]).getDateDied(), myQMap.get(idArray[i]).getSymptomText());
                }

                out.printf("\n");
            }

            out.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void insertionSortFile(HashMap<Integer, Patient> myIMap)
    {
        try
        {
            int[] idArray = new int[myIMap.size()];
            int count = 0;

            for(int id : myIMap.keySet())
            {
                idArray[count] = myIMap.get(id).getVaersID();
                count++;
            }

            int idArrayLen = idArray.length;

            for (int i = 1; i < idArrayLen; i++)
            {
                int curID = idArray[i];
                int j = i - 1;
    
                while (j >= 0 && idArray[j] > curID) 
                {
                    idArray[j + 1] = idArray[j];
                    j = j - 1;
                }

                idArray[j + 1] = curID;
            }

            PrintWriter out = new PrintWriter(new File("Insertion_Sort_VAERS_COVID_DataAugust2023.csv"));

            out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s\n", "VAERS_ID", "AGE_YRS", "SEX", "VAX_NAME", "RPT_DATE", "SYMPTOM", "DIED", "DATEDIED", "SYMPTOM_TEXT");

            for(int i = 0; i < idArray.length; i++)
            {
                for(int j = 0; j < myIMap.get(idArray[i]).getSymptoms().size(); j++)
                {
                    out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s", myIMap.get(idArray[i]).getVaersID(), myIMap.get(idArray[i]).getAgeYrs(), myIMap.get(idArray[i]).getSex(), myIMap.get(idArray[i]).getVaxName(), myIMap.get(idArray[i]).getRptDate(), myIMap.get(idArray[i]).getSymptom(j).getSymptom(), myIMap.get(idArray[i]).getDied(), myIMap.get(idArray[i]).getDateDied(), myIMap.get(idArray[i]).getSymptomText());
                }

                out.printf("\n");
            }

            out.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void selectionSortFile(HashMap<Integer, Patient> mySMap)
    {
        try
        {
            int[] idArray = new int[mySMap.size()];
            int count = 0;

            for(int id : mySMap.keySet())
            {
                idArray[count] = mySMap.get(id).getVaersID();
                count++;
            }

            int idArrayLen = idArray.length;

            for (int i = 0; i < idArrayLen - 1; i++) 
            {
                int minElem = i;

                for (int j = i + 1; j < idArrayLen; j++) 
                {
                    if (idArray[j] < idArray[minElem])
                    {
                        minElem = j;
                    }  
                }
    
                int temp = idArray[minElem];
                idArray[minElem] = idArray[i];
                idArray[i] = temp;
            }

            PrintWriter out = new PrintWriter(new File("Selection_Sort_VAERS_COVID_DataAugust2023.csv"));

            out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s\n", "VAERS_ID", "AGE_YRS", "SEX", "VAX_NAME", "RPT_DATE", "SYMPTOM", "DIED", "DATEDIED", "SYMPTOM_TEXT");

            for(int i = 0; i < idArray.length; i++)
            {
                for(int j = 0; j < mySMap.get(idArray[i]).getSymptoms().size(); j++)
                {
                    out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s", mySMap.get(idArray[i]).getVaersID(), mySMap.get(idArray[i]).getAgeYrs(), mySMap.get(idArray[i]).getSex(), mySMap.get(idArray[i]).getVaxName(), mySMap.get(idArray[i]).getRptDate(), mySMap.get(idArray[i]).getSymptom(j).getSymptom(), mySMap.get(idArray[i]).getDied(), mySMap.get(idArray[i]).getDateDied(), mySMap.get(idArray[i]).getSymptomText());
                }

                out.printf("\n");
            }

            out.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void groupFile(String sortedFile)

    {
        int[][][] diedArray = new int[12][3][4];

        BufferedReader sortedFileReader = null;
        String sortedFileLine = "";

        int curID = 0;

        try
        {
            sortedFileReader = new BufferedReader(new FileReader(sortedFile));

            sortedFileLine = sortedFileReader.readLine();
            sortedFileLine = sortedFileReader.readLine();

            while(sortedFileLine != null)
            {
                String[] sortedRow = sortedFileLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                if(curID != Integer.parseInt(sortedRow[0]))
                {
                    curID = Integer.parseInt(sortedRow[0]);

                    if(sortedRow[9].equals("Y"))
                    {
                        int ageIndex = 0;
                        int genderIndex = 0;
                        int manuIndex = 0;

                        if(sortedRow[3].equals(""))
                        {
                            ageIndex = 11;
                        }

                        else if(Double.parseDouble(sortedRow[3]) < 1f)
                        {
                            ageIndex = 0;
                        }

                        else if(Double.parseDouble(sortedRow[3]) >= 1f && Double.parseDouble(sortedRow[3]) < 4f)
                        {
                            ageIndex = 1;
                        }

                        else if(Double.parseDouble(sortedRow[3]) >= 4f && Double.parseDouble(sortedRow[3]) < 12f)
                        {
                            ageIndex = 2;
                        }

                        else if(Double.parseDouble(sortedRow[3]) >= 12f && Double.parseDouble(sortedRow[3]) < 18f)
                        {
                            ageIndex = 3;
                        }

                        else if(Double.parseDouble(sortedRow[3]) >= 19f && Double.parseDouble(sortedRow[3]) < 30f)
                        {
                            ageIndex = 4;
                        }

                        else if(Double.parseDouble(sortedRow[3]) >= 30f && Double.parseDouble(sortedRow[3]) < 40f)
                        {
                            ageIndex = 5;
                        }

                        else if(Double.parseDouble(sortedRow[3]) >= 40f && Double.parseDouble(sortedRow[3]) < 50f)
                        {
                            ageIndex = 6;
                        }

                        else if(Double.parseDouble(sortedRow[3]) >= 50f && Double.parseDouble(sortedRow[3]) < 60f)
                        {
                            ageIndex = 7;
                        }

                        else if(Double.parseDouble(sortedRow[3]) >= 60f && Double.parseDouble(sortedRow[3]) < 70f)
                        {
                            ageIndex = 8;
                        }

                        else if(Double.parseDouble(sortedRow[3]) >= 70f && Double.parseDouble(sortedRow[3]) < 80f)
                        {
                            ageIndex = 9;
                        }

                        else if(Double.parseDouble(sortedRow[3]) >= 80)
                        {
                            ageIndex = 10;
                        }

                        if(sortedRow[6].equals("M"))
                        {
                            genderIndex = 0;
                        }

                        else if(sortedRow[6].equals("F"))
                        {
                            genderIndex = 1;
                        }

                        else if(sortedRow[6].equals("U"))
                        {
                            genderIndex = 2;
                        }

                        if(sortedRow[36].equals("PFIZER/BIONTECH"))
                        {
                            manuIndex = 0;
                        }

                        else if(sortedRow[36].equals("MODERNA"))
                        {
                            manuIndex = 1;
                        }

                        else if(sortedRow[36].equals("JANSSEN"))
                        {
                            manuIndex = 2;
                        }

                        else if(sortedRow[36].equals("UNKNOWN MANUFACTURER"))
                        {
                            manuIndex = 3;
                        }

                        diedArray[ageIndex][genderIndex][manuIndex]++;
                    }
                }

                sortedFileLine = sortedFileReader.readLine();
            }

            sortedFileReader.close();

            PrintWriter groupOut = new PrintWriter(new File("COVID_Death_Data.txt"));

            for(int i = 0; i < 12; i++)
            {
                if(i == 0)
                {
                    groupOut.printf("Ages less than 1 year old:\n");
                }

                else if(i == 1)
                {
                    groupOut.printf("Ages between 1 and 3 years old:\n");
                }

                else if(i == 2)
                {
                    groupOut.printf("Ages between 4 and 11 years old:\n");
                }

                else if(i == 3)
                {
                    groupOut.printf("Ages between 12 and 18 years old:\n");
                }

                else if(i == 4)
                {
                    groupOut.printf("Ages between 19 and 30 years old:\n");
                }

                else if(i == 5)
                {
                    groupOut.printf("Ages between 31 and 40 years old:\n");
                }

                else if(i == 6)
                {
                    groupOut.printf("Ages between 41 and 50 years old:\n");
                }

                else if(i == 7)
                {
                    groupOut.printf("Ages between 51 and 60 years old:\n");
                }

                else if(i == 8)
                {
                    groupOut.printf("Ages between 61 and 70 years old:\n");
                }

                else if(i == 9)
                {
                    groupOut.printf("Ages between 71 and 80 years old:\n");
                }

                else if(i == 10)
                {
                    groupOut.printf("Ages greater than 80 years old:\n");
                }

                else if(i == 11)
                {
                    groupOut.printf("Unknown age group:\n");
                }

                for(int j = 0; j < 3; j++)
                {
                    if(j == 0)
                    {
                        groupOut.printf("Whose gender is male, ");
                    }

                    else if(j == 1)
                    {
                        groupOut.printf("Whose gender is female, ");
                    }

                    else if(j == 2)
                    {
                        groupOut.printf("Whose gender is unknown, ");
                    }

                    for(int k = 0; k < 4; k++)
                    {
                        if(k == 0)
                        {
                            groupOut.printf("and who received the Pfizer/Biontech COVID-19 Vaccine, there were these many deaths: ");
                        }

                        else if(k == 1)
                        {
                            groupOut.printf("and who received the Moderna COVID-19 Vaccine, there were these many deaths: ");
                        }

                        else if(k == 2)
                        {
                            groupOut.printf("and who received the Janssen COVID-19 Vaccine, there were these many deaths: ");
                        }

                        else if(k == 3)
                        {
                            groupOut.printf("and who received an unknown COVID-19 Vaccine, there were these many deaths: ");
                        }

                        groupOut.printf("%d\n", diedArray[i][j][k]);
                    }
                }

                groupOut.printf("\n");
            }

            groupOut.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
*/
}
