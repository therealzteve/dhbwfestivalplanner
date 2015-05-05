

import java.util.List;

import com.model.Guest;
import com.model.GuestList;

public class GuestListIdWrapper {

	private GuestList guestList;
	private int id;
	public GuestList getGuestList() {
		return guestList;
	}
	public void setGuestList(GuestList guestList) {
		this.guestList = guestList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public List<Guest> getGuests(){
		return guestList.getItems();
	}
}
