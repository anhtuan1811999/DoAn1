async function shiftPage(data,url1,url2){


    await fetch(url1, {
        headers:{
            'Content-Type':'application/json',
            'Accept': 'application/json'
        },
        method: "POST",
        body: JSON.stringify(data)
    }).then((res) => {
        if(res.status === 200){
            return res.text();
        }
        else{
            throw new Error("Error occur")
        }
    }).then((res) => {
        window.location.replace(url2);
    }).catch((e) => {
        console.log("Error ",e)
        alert("sai ten dang nhap hoac mat khau")
    })
}
