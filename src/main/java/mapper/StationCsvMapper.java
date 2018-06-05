package mapper;

import models.StationEntity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StationCsvMapper {

    public StationCsvMapper(String fileLocation) {
        this.fileLocation = fileLocation;
        logger = Logger.getLogger(StationCsvMapper.class.getName());
    }

    private final String fileLocation;
    private Logger logger;
    private List<StationEntity> preparedList = new ArrayList<>();

    public List<StationEntity> getPreparedStationList(){
        try {
            String fileLine;
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            while((fileLine = reader.readLine()) != null){
                prepareStationEntity(fileLine);
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "Not found file for given location");
        } catch (IOException e) {
            logger.log(Level.WARNING, "Wrong file");
        }
        return preparedList;
    }

    private void prepareStationEntity(String fileLine) {
        String splitBy = ";";
        String [] splitedLine = fileLine.split(splitBy);
        StationEntity station = new StationEntity();
        for(int columnNum = 0; columnNum < splitedLine.length; columnNum++){
            switch(columnNum){
                    case 0 : station.setState(splitedLine[columnNum]); break;
                    case 1 : station.setOldCode(splitedLine[columnNum]); break;
                    case 2 : station.setCode(splitedLine[columnNum]); break;
                    case 3 : station.setStationName(splitedLine[columnNum]); break;
                    case 4 : station.setCity(splitedLine[columnNum]); break;
                    case 5 : station.setAddress(splitedLine[columnNum]); break;
                }
        }
        preparedList.add(station);

    }


}
