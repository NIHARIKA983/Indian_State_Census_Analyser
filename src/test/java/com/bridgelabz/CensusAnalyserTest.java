package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class CensusAnalyserTest {

    private static final String CORRECT_FILE_PATH = "\"C:\\Users\\KVN\\IdeaProjects\\CensusAnalyser\\src\\test\\resources\\IndiaStateCensusData.csv\"";
    private static final String WRONG_FILE_PATH = "\"C:\\Users\\KVN\\IdeaProjects\\CensusAnalyser\\src\\test\\resources\\WrongFile.txt\"";
    private static final String WRONG_CSV_TYPE = "\"C:\\Users\\KVN\\IdeaProjects\\CensusAnalyser\\src\\test\\resources\\SampleForHeaderAndDelimiter.csv\"";
    private static final String STATE_CODE_FILE_PATH = "\"C:\\Users\\KVN\\IdeaProjects\\CensusAnalyser\\src\\test\\resources\\IndiaStateCode.csv\"";



    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(CORRECT_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenWrongFilePthThrowCustomException() {

        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_FILE_PATH);
        } catch (CensusAnalyserException e) {
            System.out.println(e.type);
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NOT_A_CSV_TYPE_OR_HEADERS_INVALID,e.type);
        }
    }
    @Test
    public void givenIndiaCensusData_WhenWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_TYPE);
        }catch (CensusAnalyserException e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NOT_A_CSV_TYPE_OR_HEADERS_INVALID,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WhenDelimiterIncorrect_ShouldThrowException() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(STATE_CODE_FILE_PATH);
        }catch(CensusAnalyserException  e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NOT_A_CSV_TYPE_OR_HEADERS_INVALID,e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCode(STATE_CODE_FILE_PATH);
            Assert.assertEquals(37,numOfRecords);
        } catch (CensusAnalyserException e) { }
    }

    @Test
    public void givenIndiaStateCode_WhenWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateCode(WRONG_CSV_TYPE);
        }catch (CensusAnalyserException e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NOT_A_CSV_TYPE_OR_HEADERS_INVALID, e.type);
        }
    }
}
