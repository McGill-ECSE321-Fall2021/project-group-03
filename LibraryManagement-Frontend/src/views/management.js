import axios from 'axios'
import MenuBar from '../components/MenuBar.vue'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function LibrarianDto (username, password, fullName, isHeadLibrarian){
    this.username = username
    this.password = password
    this.fullName = fullName
    this.isHeadLibrarian = isHeadLibrarian
}

    export default{
     
        methods:  {

            createLibraryScheduleTimeslot(){
                // get info from inputs
                const startDate = document.getElementById("timeslot-date").value.split("-"),
                //startTime = document.getElementById("ts").value.split(":"),
                //endTime = document.getElementById("te").value.split(":"),
                //startHour = startTime[0],
                //startMin = startTime[1],
                //endHour = endTime[0],
                //endMin = endTime[1],
                year = startDate[0],
                month = startDate[1],
                day = startDate[2]

                const dateLibraryScheduleMsg = document.getElementById("msg-date-library-schedule");
                const startTimeLibraryScheduleMsg = document.getElementById("msg-start-time-library-schedule");
                const endTimeLibraryScheduleMsg = document.getElementById("msg-end-time-library-schedule");
                const setLibraryScheduleMsg = document.getElementById("msg-set-time-library-schedule");
                
                dateLibraryScheduleMsg.innerHTML=""
                startTimeLibraryScheduleMsg.innerHTML=""
                endTimeLibraryScheduleMsg.innerHTML=""
                setLibraryScheduleMsg.innerHTML=""
                
                console.log("shalom dudes")

                let startHourNew = document.getElementById("library-schedule-start-hour").value
                let startHourInt = parseInt(startHourNew)
                let startHourAMorPM = document.getElementById("library-schedule-start-hour-am-pm").value

                let endHourNew = document.getElementById("library-schedule-end-hour").value
                let endHourInt = parseInt(endHourNew)
                let endHourAMorPM = document.getElementById("library-schedule-end-hour-am-pm").value

                if (startHourAMorPM === "PM"){
                    startHourInt = startHourInt + 12;
                }

                if (endHourAMorPM === "PM"){
                    endHourInt = endHourInt + 12;
                }

                //handle input errors

                if (year === "" || month==="" || day==="" ) {
                    dateLibraryScheduleMsg.innerHTML = "Library time slot cannot be empty. Please fill in the year, month and day";
                    dateLibraryScheduleMsg.style.color = "red";
                    return
                  }

                if (startHourNew === "Start Time" || startHourAMorPM === "AM or PM" ) {
                    startTimeLibraryScheduleMsg.innerHTML = "Error. Please fill in the Start Time and AM/PM fields";
                    startTimeLibraryScheduleMsg.style.color = "red";
                    return
                  }

                  if (endHourNew === "End Time" | endHourAMorPM === "AM or PM" ) {
                      console.log("we are entered")
                    endTimeLibraryScheduleMsg.innerHTML = "Error. Please fill in the End Time and AM/PM fields";
                    endTimeLibraryScheduleMsg.style.color = "red";
                    return
                  }

                //handles other errors

                if (endHourInt <= startHourInt ){
                    startTimeLibraryScheduleMsg.innerHTML = "Start time must be before end time";
                    startTimeLibraryScheduleMsg.style.color = "red";
                    return
                }

                var currentDate = new Date();
                var inputDate = new Date(year, month-1, day, 0, 0, 0, 0)
                //unclear why the month is off by 1....but this seems to work
                //In postman, there is the right month so I guess this works

                if (currentDate > inputDate){
                    dateLibraryScheduleMsg.innerHTML = "Library time slot cannot be created in the past";
                    dateLibraryScheduleMsg.style.color = "red";
                    return
                }


                let startHourString = startHourInt.toString()
                let endHourString = endHourInt.toString()

                // create url
                const url = "/libraryTimeslots/create/" + startHourString + "?startMin=" + "0" + "&endHour=" + endHourString + "&endMin=" + "0" + "&year=" + year + "&month=" + month + "&day=" + day;

                AXIOS.post(url, {}, {}).then(response => {
                    console.log(response.data)
                    console.log("great success")
                    setLibraryScheduleMsg.innerHTML = "Library hours added succesfully";
                    setLibraryScheduleMsg.style.color = "green";
                })
                .catch(e => {
                    setLibraryScheduleMsg.innerHTML = "Library time slot cannot overlap existing time slot";
                    setLibraryScheduleMsg.style.color = "red";

                });
            },

            createStaffScheduleTimeslot(){
                

                const libUsernameMsg = document.getElementById("msg-lib-username-staff-schedule");
                const dateStaffScheduleMsg = document.getElementById("msg-date-staff-schedule");
                const startTimeStaffScheduleMsg = document.getElementById("msg-start-time-staff-schedule");
                const endTimeStaffScheduleMsg = document.getElementById("msg-end-time-staff-schedule");
                const setStaffScheduleMsg = document.getElementById("msg-set-time-staff-schedule");

                //notice: there are commas between the stuff when using the split method
                const startDateStaff = document.getElementById("timeslot-date-staff").value.split("-"),
                yearStaff = startDateStaff[0],
                monthStaff = startDateStaff[1],
                dayStaff = startDateStaff[2];


                 libUsernameMsg.innerHTML=""
                 dateStaffScheduleMsg.innerHTML=""
                 startTimeStaffScheduleMsg.innerHTML=""
                 endTimeStaffScheduleMsg.innerHTML=""
                setStaffScheduleMsg.innerHTML=""


                


                let librarianUsername = document.getElementById("lib-username").value;
                let startHour = document.getElementById("staff-schedule-start-hour").value;
                let startHourInt = parseInt(startHour);
                let startHourAMorPM = document.getElementById("staff-schedule-start-hour-am-pm").value;
                let endHour = document.getElementById("staff-schedule-end-hour").value;
                let endHourInt = parseInt(endHour);
                let endHourAMorPM = document.getElementById("staff-schedule-end-hour-am-pm").value;

                

               


                if (librarianUsername === ""){
                    libUsernameMsg.innerHTML = "Librarian username cannot be empty"
                    libUsernameMsg.style.color = "red"
                    return
                }

                if (yearStaff === "" || monthStaff==="" || dayStaff==="" ) {
                    dateStaffScheduleMsg.innerHTML = "Library time slot cannot be empty. Please fill in the year, month and day";
                    dateStaffScheduleMsg.style.color = "red";
                  return
                }


                if (startHour === "Start Time" || startHourAMorPM === "AM or PM" ) {
                    startTimeStaffScheduleMsg.innerHTML = "Error. Please fill in the Start Time and AM/PM fields";
                    startTimeStaffScheduleMsg.style.color = "red";
                      return
                }
  
                if (endHour === "End Time" | endHourAMorPM === "AM or PM" ) {
                        console.log("we are entered")
                        endTimeStaffScheduleMsg.innerHTML = "Error. Please fill in the End Time and AM/PM fields";
                        endTimeStaffScheduleMsg.style.color = "red";
                      return
                }

                if (startHourAMorPM === "PM"){
                    console.log("in the if")
                    startHourInt = startHourInt + 12;
                }

                if (endHourAMorPM === "PM"){
                    console.log("in the if")
                    endHourInt = endHourInt + 12;
                }

                if (endHourInt <= startHourInt ){
                    startTimeStaffScheduleMsg.innerHTML = "Start time must be before end time";
                    startTimeStaffScheduleMsg.style.color = "red";
                    return
                }


                
            
                  var currentDate = new Date();
                  var inputDate = new Date(yearStaff, monthStaff-1, dayStaff, 0, 0, 0, 0)
                  //unclear why the month is off by 1....but this seems to work
                  //In postman, there is the right month so I guess this works
                  console.log("hiiiiiii")
                  console.log(currentDate)
                  console.log("hiiiiiii hhh")
                  console.log(inputDate)
  
                  if (currentDate > inputDate){
                     dateStaffScheduleMsg.innerHTML = "Library time slot cannot be created in the past";
                     dateStaffScheduleMsg.style.color = "red";
                      return
                  }
        

                // create url
                const url = "/staffSchedules/create/" + librarianUsername + "?startHour=" + startHour + "&startMin=" + "0" + "&endHour=" + endHour + "&endMin=" + "0" + "&year=" + yearStaff + "&month=" + monthStaff + "&day=" + dayStaff;

                AXIOS.post(url, {}, {}).then(response => {
                    console.log(response.data)
                    setStaffScheduleMsg.innerHTML= "Succesfully added"
                    setStaffScheduleMsg.style.color = "green"
                })
                .catch(e => {

                    setStaffScheduleMsg.innerHTML="This operation did not work. Please make sure this time slot does not overlap with an existing time slot and that the time slot is within the opening hours of the library. "
                    setStaffScheduleMsg.style.color = "red"
                });
            },

            expandLibraryScheduling(){
                const content = document.getElementById("library-schedule")
                const icon = document.getElementById("arrow-library-scheduling")

                if (content.style.display == "none"){
                    content.style.display = "block"
                    icon.innerHTML = "-"
                }

                else {
                    content.style.display = "none"
                    icon.innerHTML = "+"
                }
            },

            expandStaffScheduling(){
                const content = document.getElementById("staff-schedule")
                const icon = document.getElementById("arrow-staff-scheduling")

                if (content.style.display == "none"){
                    content.style.display = "block"
                    icon.innerHTML = "-"
                }

                else {
                    content.style.display = "none"
                    icon.innerHTML = "+"
                }
            },
            expandLibrarianManagement(){
                const content = document.getElementById("librarian-management")
                const icon = document.getElementById("arrow-librarian-management")

                if (content.style.display == "none"){
                    content.style.display = "block"
                    icon.innerHTML = "-"
                }

                else {
                    content.style.display = "none"
                    icon.innerHTML = "+"
                }
            },
            hireLibrarian() {
                let librarianUsername = document.getElementById("librarian-hire-username").value
                console.log(librarianUsername)
                let librarianPassword = document.getElementById("librarian-hire-password").value
                console.log(librarianPassword)
                let librarianFullName = document.getElementById("librarian-hire-fullname").value
                console.log(librarianFullName)

                let goodUrl = "/librarians/create/" + librarianUsername + "?password=" + librarianPassword + "&fullName=" + librarianFullName + "&isHeadLibrarian=false"
                
                const hireLibrarianMsg = document.getElementById("msg-create-librarian");

                if (librarianUsername === "") {
                    hireLibrarianMsg.innerHTML = "Librarian username cannot be empty!";
                    hireLibrarianMsg.style.color = "red";
                    return
                  }
            
                  if (librarianPassword === "") {
                    hireLibrarianMsg.innerHTML = "Librarian password cannot be empty!";
                    hireLibrarianMsg.style.color = "red";
                    return
                  }
            
                  if (librarianFullName === "") {
                    hireLibrarianMsg.innerHTML = "Librarian Full Name cannot be empty!";
                    hireLibrarianMsg.style.color = "red";
                    return
                  }

                AXIOS.post(goodUrl, {}, {}).then(response => {
                    //var t = new LibrarianDto (librarianUsername, librarianPassword, librarianFullName, false)
                    //this.titles.push(t)

                    hireLibrarianMsg.innerHTML = "Librarian hired succesfully";
                    hireLibrarianMsg.style.color = "green";
                    
                    })
                    .catch(e => {
                        hireLibrarianMsg.innerHTML = "Username already exists";
                        hireLibrarianMsg.style.color = "red";
                      })
            },

            fireLibrarian() {
                let librarianUsername = document.getElementById("librarian-fire-username").value

                let goodUrl = "/librarians/remove/" + librarianUsername
                console.log(goodUrl)

                const fireLibrarianMsg = document.getElementById("msg-fire-librarian");

                if (librarianUsername === "") {
                    fireLibrarianMsg.innerHTML = "Librarian does not exist!";
                    fireLibrarianMsg.style.color = "red";
                    return
                  }

                if (librarianUsername == localStorage.getItem("Username")){
                    fireLibrarianMsg.innerHTML = "Cannot fire head librarian!";
                    fireLibrarianMsg.style.color = "red";
                    return
                }

                AXIOS.post(goodUrl, {}, {}).then(response => {
                    //var t = new LibrarianDto (librarianUsername, librarianPassword, librarianFullName, false)
                    //this.titles.push(t)
                    fireLibrarianMsg.innerHTML = "Librarian fired succesfully!";
                    fireLibrarianMsg.style.color = "green";
                    })
                    .catch(e => {
                        fireLibrarianMsg.innerHTML = "Librarian does not exist!";
                        fireLibrarianMsg.style.color = "red";
                      })
            }
        },   
        components: {
            MenuBar,
        }
}