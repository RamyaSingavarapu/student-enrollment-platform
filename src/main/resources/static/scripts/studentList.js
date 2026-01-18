let onDomContentLoaded = () => {
    let showMoreButton = document.getElementById("showMore");
    let pageNumber = 0;

    showMoreButton.addEventListener("click", async () => {
        pageNumber++;

        let requestPromise = fetch("/students?pageNumber=" + pageNumber, {
            method: "GET",
            headers: {
                Accept: 'application/json'
            }
        });

        // requestPromise.then((response) => { ... });

        let response = await requestPromise; //byte stream

        let jsonResponse = await response.json();
        console.log(jsonResponse)
        let listOfStudents = document.getElementById("listOfStudents");
        for (let i = 0; i < jsonResponse.studentList.length; i++) {
            let student = jsonResponse.studentList[i];

            let ul = document.createElement("ul");

            let liLast = document.createElement("li");
            liLast.textContent = "LastName:";

            let spanLast = document.createElement("span");
            spanLast.textContent = student.lastName;

            liLast.appendChild(spanLast);
            ul.appendChild(liLast);

            let liRoll = document.createElement("li");
            liRoll.textContent = "RollNum:";

            let spanRoll = document.createElement("span");
            spanRoll.textContent = student.rollNumber;

            liRoll.appendChild(spanRoll);
            ul.appendChild(liRoll);

            let liDetails = document.createElement("li");
            liDetails.textContent = "Details:";

            let a = document.createElement("a");
            a.textContent = "link";
            a.href = "/student/" + student.id;

            liDetails.appendChild(a);
            ul.appendChild(liDetails);

            listOfStudents.appendChild(ul);

        }
    });
};

document.addEventListener("DOMContentLoaded", onDomContentLoaded)

