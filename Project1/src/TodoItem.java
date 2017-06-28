import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TodoItem {

	private String description;
	private Date dateAdded;
	private Date dateDue;
	private DateFormat df;
	private boolean status;

	public TodoItem(String description, int days) {
		this.description = description;
		setDates(days, true);
		df = new SimpleDateFormat("dd/MM/yyyy");
		status = false;
	}

	public void setDates(int daysToDueDate, boolean isNewItem) {
		GregorianCalendar gc = new GregorianCalendar();

		if (isNewItem)
			dateAdded = gc.getTime();
		gc.add(Calendar.DAY_OF_MONTH, daysToDueDate);
		dateDue = gc.getTime();
	}

	public void setDone() {
		status = true;
	}

	public void setUnDone() {
		status = false;
	}

	public boolean getStatus() {
		return status;
	}

	private String getStatusString() {
		if (status)
			return "Yes";
		else
			return "No";
	}

	public String getDescription() {
		return description;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public Date getDateDue() {
		return dateDue;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString(int index) {
		return String.format("%5d%20s%16s%16s%12s", index, description, df.format(dateDue), df.format(dateAdded),
				getStatusString());
	}

}
