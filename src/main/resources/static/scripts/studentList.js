document.addEventListener("DOMContentLoaded",()=>{
    let showMoreButton = document.getElementById("showMore");
    let pageNumber = 0;
    showMoreButton.addEventListener("click", async ()=>{
        pageNumber++;

        let requestPromise = fetch("/students?pageNumber="+pageNumber, {
            method: "GET",
            headers: {
                Accept: 'application/json'
            }
        });

        let response = await requestPromise; //byte stream

        let jsonResponse = await response.json();

        console.log(jsonResponse);
    });
})

