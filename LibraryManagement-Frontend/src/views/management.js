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
                startTime = document.getElementById("ts").value.split(":"),
                endTime = document.getElementById("te").value.split(":"),
                startHour = startTime[0],
                startMin = startTime[1],
                endHour = endTime[0],
                endMin = endTime[1],
                year = startDate[0],
                month = startDate[1],
                day = startDate[2]

                // create url
                const url = "/libraryTimeslots/create/" + startHour + "?startMin=" + startMin + "&endHour=" + endHour + "&endMin=" + endMin + "&year=" + year + "&month=" + month + "&day=" + day;

                console.log(url)

                AXIOS.post(url, {}, {}).then(response => {
                    console.log(response.data)
                })
                .catch(e => {

                });
            },

            createStaffScheduleTimeslot(){
                // get info from inputs
                const startDate = document.getElementById("staff-timeslot-date").value.split("-"),
                startTime = document.getElementById("ts-staff").value.split(":"),
                endTime = document.getElementById("te-staff").value.split(":"),
                startHour = startTime[0],
                startMin = startTime[1],
                endHour = endTime[0],
                endMin = endTime[1],
                year = startDate[0],
                month = startDate[1],
                day = startDate[2],
                librarianUsername = document.getElementById("lib-username").value

                // create url
                const url = "/staffSchedules/create/" + librarianUsername + "?startHour=" + startHour + "&startMin=" + startMin + "&endHour=" + endHour + "&endMin=" + endMin + "&year=" + year + "&month=" + month + "&day=" + day;

                console.log(url)

                AXIOS.post(url, {}, {}).then(response => {
                    console.log(response.data)
                })
                .catch(e => {

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
                console.log(goodUrl)
                
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
                let librarianId = document.getElementById("librarian-fire-username").value
                console.log(librarianId)

                let goodUrl = "/librarians/remove/" + librarianId
                console.log(goodUrl)

                const fireLibrarianMsg = document.getElementById("msg-fire-librarian");

                if (librarianId === "") {
                    fireLibrarianMsg.innerHTML = "Librarian does not exist!";
                    fireLibrarianMsg.style.color = "red";
                    return
                  }

                AXIOS.post(goodUrl, {}, {}).then(response => {
                    //var t = new LibrarianDto (librarianUsername, librarianPassword, librarianFullName, false)
                    //this.titles.push(t)
                    fireLibrarianMsg.innerHTML = "Librarian fired succesfully";
                    fireLibrarianMsg.style.color = "green";
                    })
                    .catch(e => {
                        fireLibrarianMsg.innerHTML = "Cannot fire head librarian";
                        fireLibrarianMsg.style.color = "red";
                      })
            }
        },   
        components: {
            MenuBar,
        }
}