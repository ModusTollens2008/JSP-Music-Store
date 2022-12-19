

var listNames =['ser','per','mer'];

function getNames(input){

    if(input !="")
        $.ajax({
            url: 'compshandler',
            dataType: "json",
            data: { // передаваемые сервлету данные
                input: input
            },
            success: function(resultData){
                listNames = resultData;
            }
        });
}

function setOption(input){
    let res = "";
    inputG = input;
    getNames(input);
    for (var i = 0; i<listNames.length; i++)
    {
        res+= "<option>" + listNames[i] + "</option>";
    }
    console.log('update');
    document.getElementById("options").innerHTML = res;
}