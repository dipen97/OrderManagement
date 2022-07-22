let host = 'http://10.3.187.73:7001/v1';
let host2 = 'http://localhost:8080/v1';
let numberOfProducts = 1;
let formNumber = 1;



const form = document.forms.namedItem("newOrder")
form.addEventListener('submit', function(ev) {
    const formData = new FormData(form);
    /*const xhttp = new XMLHttpRequest();
    xhttp.open('POST', host + '/orders/new2', true);
    xhttp.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');*/

const url = 'http://localhost:8080/v1/orders/new2';
console.log("formdata",formData)
const data =[ 
	{name : document.getElementById("name").value,
	price : document.getElementById("price").value,
	manufacturer : document.getElementById("manufacturer").value}
]
//alert(data.length); //debug test
	fetch(url, {
		headers: {
			'content-type': 'application/json;charset=utf-8',
		},
                 method: 'POST', body: JSON.stringify(data)
            })
	.then(
		console.log('got response'),
		response => response.json(),
	).then(
		data => {
			console.log(data)
			window.location.href = "/html/manager.html";
		}
		/*data =>{
			//insert loop
			let temp = ""
			data.forEach(element => {
                        temp += "<tr>";
                        temp += "<td class='align-middle'>" + element.id + "</td>";
                        temp += "<td class='align-middle'>" + element.created + "</td>";
                        //temp += "<td class='align-middle'>" + element.lastName + "</td>";
                        //temp += "<td class='align-middle'>" + element.phoneNumber + "</td>";
                        //temp += "<td class='align-middle'>" + element.policyNumber + "</td>";
                        //temp += "<td class='align-middle'>" + element.amount + "</td>";
                        //temp += "<td class='align-middle'>" + element.status + "</td>";
                        //temp += "<td class='align-middle'>" + `<button type="button" class="btn btn-outline-warning btn-sm" onclick="viewDetails(${element.id})">View</button>` + "</td>";
                        temp += "</tr>"


                    });
                    myData.innerHTML = temp;
			console.log(data);
			console.log("data id = ",data[1].id);
		}*/
	).catch(e=>{
		console.log(e);
	})
	

    var arr = [];
    var formDataObject = Object.fromEntries(formData.entries());

    for (var x = 0; x < numberOfProducts; x++) {
      var object = {};
      for (const pair in formDataObject) {
        if (pair == "name" + x) {
          object["name"] = formDataObject[pair];
        } else if (pair == "price" + x) {
          object["price"] = formDataObject[pair];
        } else if (pair == "manufacturer" + x) {
          object["manufacturer"] = formDataObject[pair];
        }
      }
      arr.push(object);
    }
    //xhttp.send(JSON.stringify(arr));x
    ev.preventDefault();
    
  }, false);    

  function addProduct() {
    numberOfProducts++;

    var div = document.createElement("div");
    div.setAttribute("class", "col-auto");
    var name = document.createElement("input");
    name.setAttribute("name", "name" + formNumber);
    name.setAttribute("class", "form-control");
    name.setAttribute("placeholder", "Product Name");
    name.setAttribute("type", "text");
    name.setAttribute("placeholder", "Product Name");

    var price = document.createElement("input");
    price.setAttribute("name", "price" + formNumber);
    price.setAttribute("class", "form-control");
    price.setAttribute("placeholder", "Price USD");
    price.setAttribute("type", "number");
    price.setAttribute("step", "0.01");
    price.setAttribute("min", "0.01");
    
    var manufacturer = document.createElement("input");
    manufacturer.setAttribute("name", "manufacturer" + formNumber);
    manufacturer.setAttribute("class", "form-control");
    manufacturer.setAttribute("placeholder", "Manufacturer")
    manufacturer.setAttribute("type", "text");

    div.appendChild(name);
    div.appendChild(price);
    div.appendChild(manufacturer);
    // div.appendChild(form);

    var outer = document.querySelector("#newOrder");
    outer.appendChild(div);
    formNumber++;
  }
