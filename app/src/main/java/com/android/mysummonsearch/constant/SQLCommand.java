package com.android.mysummonsearch.constant;

public abstract class SQLCommand
{
    public static String SEARCH_QUERY = "Select LibBook.BookId as _id, LibBook.BookName,Author.AuthorName,Publisher.PublisherName,LibBook.ShelfNo from LibBook,Publisher,Author where LibBook.PublisherId=Publisher.PublisherId and LibBook.AuthorId=Author.AuthorId and (BookName like ? or AuthorName like ?)";
    public static String BORROW_BOOK_STU = "Select Student.StId as _id,BookName,DueDate from Checkout,LibBook,Student where Checkout.BookId=LibBook.BookId and Checkout.StId=Student.StId and Student.StId=? and Checkout.ActualReturnDate IS NULL";
    public static String BORROW_BOOK_EMP = "Select Checkout.BookId as _id,StName,BookName,DueDate from Checkout,LibBook,Student where Checkout.BookId=LibBook.BookId and Checkout.StId=Student.StId and CheckOut.ActualReturnDate IS NULL";
    public static String Pending_fines = "select strftime('%X', FineAmount) as Amount, count(*) as Total from Dues where DuesCleared = 'N' group by Amount";
    public static String borrow = "select strftime('%X', BookId) as BookID, count(*) as Total from CheckOut group by BookID";
    public static String seat_history = "select strftime('%X', SeatId) as SeatNo, count(*) as Total from SeatBooking  group by SeatNo";
    public static String INC_COPIES = "Update LibBook set NoOfCopies=(Select NoOfCopies from LibBook where BookId=?)+1 where BookId=?";
    public static String DEC_COPIES ="Update LibBook set NoOfCopies=(Select NoOfCopies from LibBook where BookId=?)-1 where BookId=?";
    public static String ADD_BAL="update student set StBalance=(select StBalance from student where StId=?)+? where StId=?;";
    public static String CHK_BAL = "select StBalance as StBalance from student where StId=?";
    public static String CLR_FINE = "Update Dues set DuesCleared='Y' where Checkout.BookIssueNo IN (Select BookIssueNo from Checkout,Dues where StId=? and Checkout.BookIssueNo=Dues.BookIssueNo)";
    public static String CHK_FINE= "select NVL(Sum(FineAmount),0) from Dues where StId=? and DuesCleared='N' ";
    //public static String RESERVE_BOOK = "Insert into CheckOut(BookId, StId ,IssueDate,ActualReturnDate ,DueDate) values (?,?,Date('now'),null,Date('now','+14 days'));";
    public static String RESERVE_BOOK = "Insert into CheckOut(BookId, StId ,IssueDate,ActualReturnDate ,DueDate) values (?,?,Date('now'),null,Date('2015-12-05'));";
    public static String UPD_BK_STATUS = "Update Checkout Set ActualReturnDate = Date('now') where BookIssueNo=?";
    public static String STUDENT_ID = "select StId from Student";
    public static String STUDENT_NAME = "select StName from Student";
    public static String EMPLOYEE_ID = "select EmpId from Employee";
    public static String EMPLOYEE_NAME = "select EmpName from Employee";
    public static String SEAT_VALIDATION = "select SeatId as _id, Available from SeatBooking";
    public static String CANCEL_AVAILABLE = "update SeatBooking set Available = 'Y' where SeatId = ?";
    public static String FEEDBACK_Q = "insert into Feedback(StId, FmID, Description) values(?, ?, ?)";
    public static String GET_BOOKISSUE_NO = "Select BookIssueNo from Checkout where BookId=? and stId=(select StId from student where stName=?) and ActualReturnDate=?";
    public static String GET_DD = "Select DueDate from Checkout where BookIssueNo=?";
    public static String AVAILABLE_SEATS = "select SeatId from SeatBooking where SbDate = ? and StartTime = ? and Available = 'N'";
    public static String INSERT_AVAILABILITY = "Insert into SeatBooking(SeatId,StId,Available,SbDate,StartTime,EndTime) values(?,?,’N’,?,?,?)";
    public static String INS_DUES = "Insert into Dues values(?,'N',?)";
    public static String BOOK_SEAT = "insert into SeatBooking(SeatId, Available, SbDate, StartTime, EndTime) values ( ?, ?, ?, ?, ?)";
}
