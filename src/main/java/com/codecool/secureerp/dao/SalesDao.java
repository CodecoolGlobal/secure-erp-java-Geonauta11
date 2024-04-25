package com.codecool.secureerp.dao;
import com.codecool.secureerp.model.SalesModel;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalesDao extends Dao<SalesModel> {
    private final static int ID_TABLE_INDEX = 0;
    private final static int CUSTOMER_ID_TABLE_INDEX = 1;
    private final static int PRODUCT_TABLE_INDEX = 2;
    private final static int PRICE_TABLE_INDEX = 3;
    private final static int TRANSACTION_DATE_TABLE_INDEX = 4;
    private final static String DATA_FILE = "src/main/resources/sales.csv";
    public static String[] headers = {"Id", "Customer Id", "Product", "Price", "Transaction Date"};

    public SalesDao() throws IOException {
        super(headers);
    }

    public List<SalesModel> loadData() throws IOException {
        return super.loadData(DATA_FILE);
    }

    public void save(List<SalesModel> data) throws IOException {
        super.save(DATA_FILE, data);

    }

    protected SalesModel arrayToModel(String[] array) {
        String id = array[ID_TABLE_INDEX];
        String customerId = array[CUSTOMER_ID_TABLE_INDEX];
        String product = array[PRODUCT_TABLE_INDEX];
        double price = Double.parseDouble(array[PRICE_TABLE_INDEX]);
        String date = array[TRANSACTION_DATE_TABLE_INDEX];
        return new SalesModel(id, customerId, product, price, date);
    }

    protected String[] modelToArray(SalesModel sale) {
        String[] salesArray = new String[5];
        salesArray[ID_TABLE_INDEX] = sale.id();
        salesArray[CUSTOMER_ID_TABLE_INDEX] = sale.customerId();
        salesArray[PRODUCT_TABLE_INDEX] = sale.productName();
        salesArray[PRICE_TABLE_INDEX] = sale.price() + "";
        salesArray[TRANSACTION_DATE_TABLE_INDEX] = sale.transactionDate();

        return salesArray;
    }

    public SalesModel getSaleWithBiggestRevenue () throws IOException {
        List<SalesModel> data = loadData();
        SalesModel saleWithBiggestRevenue = data.get(0);
        for (SalesModel sale : data) {
            if (sale.price() > saleWithBiggestRevenue.price()){
                saleWithBiggestRevenue = sale;
            }
        }
        return saleWithBiggestRevenue;
    }

    public String getBiggestRevenueProduct () throws IOException {
        List<SalesModel> data = loadData();
        List<String> productRecords = getRecords(data);
        String biggestRevenueProduct = productRecords.get(0);
        double biggestRevenue = 0;

        for (String record : productRecords) {
            double currentRevenue = 0;
            for (SalesModel sale : data) {
                if (sale.productName().equals(record)) {
                    currentRevenue += sale.price();
                }
            }
            if (currentRevenue > biggestRevenue) {
                biggestRevenue = currentRevenue;
                biggestRevenueProduct = record;
            }
        }
        return biggestRevenueProduct;
    }

    private List<String> getRecords (List<SalesModel> sales){
        List<String> productRecords = new ArrayList<>();
        for (SalesModel sale : sales) {
            if (!productRecords.contains(sale.productName())){
                productRecords.add(sale.productName());
            }
        }
        return productRecords;
    }


    private List<SalesModel> getSalesBetweenDates (String dateFrom, String dateTo) throws IOException {
        List<SalesModel> data = loadData();
        List<SalesModel> sales = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        startDate = startDate.minusDays(1);
        LocalDate endDate = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        for (SalesModel sale : data){
            LocalDate saleDate = LocalDate.parse(sale.transactionDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (saleDate.isAfter(startDate) && saleDate.isBefore(endDate)){
                sales.add(sale);
            }
        }
        return sales;
    }


    public int getNumberOfSalesBetweenDates (String dateFromInput, String dateToInput) throws IOException {
        return getSalesBetweenDates(dateFromInput, dateToInput).size();
    }

    public double getSumOfRevenueBetweenDates(String dateFromInput, String dateToInput) throws IOException {
        double sum = 0;
        List<SalesModel> sales = getSalesBetweenDates(dateFromInput, dateToInput);
        for (SalesModel sale : sales){
            sum += sale.price();
        }
        return sum;
    }
}
