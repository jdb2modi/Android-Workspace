package com.ifactory.myexpenditure;

public class ExpenceModel {

	int ExpenseId, ExpenseAmount;
	String ExpenseCategory, ExpenseDate, ExpenseMode, ChequeNo, TransactionId,
			Description;

	public int getExpenseId() {
		return ExpenseId;
	}

	public void setExpenseId(int expenceId) {
		ExpenseId = expenceId;
	}

	public int getExpenseAmount() {
		return ExpenseAmount;
	}

	public void setExpenseAmount(int expenceAmount) {
		ExpenseAmount = expenceAmount;
	}

	public String getExpenseCategory() {
		return ExpenseCategory;
	}

	public void setExpenseCategory(String expenseCategory) {
		ExpenseCategory = expenseCategory;
	}

	public String getExpenseDate() {
		return ExpenseDate;
	}

	public void setExpenseDate(String expenseDate) {
		ExpenseDate = expenseDate;
	}

	public String getExpenseMode() {
		return ExpenseMode;
	}

	public void setExpenseMode(String expenseMode) {
		ExpenseMode = expenseMode;
	}

	public String getChequeNo() {
		return ChequeNo;
	}

	public void setChequeNo(String chequeNo) {
		ChequeNo = chequeNo;
	}

	public String getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
