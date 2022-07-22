let host = 'http://10.3.187.73:7001/v1';
let host2 = 'http://localhost:8080/v1';

const search = document.getElementById('search');
search.addEventListener('click', function() {
    displaySearch(document.getElementById("searchInput").value);
});
// console.log(document.getElementById("searchInput").value);
// displaySearch(document.getElementById("searchInput").value)
window.onload = function() {
    getOrders();
}

async function getOrders() {
	
	const url = host2+'/orders';
	fetch(url)
	.then(
		response => response.json()
	).then(
		data =>{
			//insert loop
			let temp = ""
			data.forEach(element => {
                        temp += "<tr>";
                        temp += "<td class='align-middle'>" + element.id + "</td>";
                        temp += "<td class='align-middle'>" + new Date (element.created).toLocaleDateString()+ "           " + new Date(element.created).toLocaleTimeString() + "</td>";
                        temp += "<td class='align-middle'>" + "<button type='button' class='btn-lg btn-info' onclick=''>View</button>"
                        temp += "</tr>"


                    });
                    myData.innerHTML = temp;
			console.log(data);
			console.log("data id = ",data[1].id);
		}
	).catch(e=>{
		console.log(e);
	})
	

    /** let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {

        if (xhttp.readyState==4 && xhttp.status==200) {
            let orders = JSON.parse(xhttp.responseText);
                loadOrders(orders);
        }
    }

    xhttp.open('GET', host + '/orders');
    xhttp.send(); */
}

async function getAllProducts() {
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {

        if (xhttp.readyState==4 && xhttp.status==200) {
            let products = JSON.parse(xhttp.responseText);
                loadAllProducts(products);
        }
    }

    xhttp.open('GET', host + '/products/all');
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send();
}


async function loadOrders(orders) {

    for (let x = 0; x < orders.length; x++) {
        
        var thead = document.createElement("thead")
        var tbody = document.createElement("tbody");
        
        var tr = document.createElement("tr");
        
        var td1 = document.createElement("td");
        var td2 = document.createElement("td");
        
        var id = document.createTextNode(orders[x].id);
        var date = document.createTextNode(orders[x].created);
        
        td1.appendChild(id);
        td2.appendChild(date);
        
        tr.appendChild(td1);
        tr.appendChild(td2);
        thead.appendChild(tr);
        var selection = document.querySelector("#display");
        thead.setAttribute("id", "order" + orders[x].id)
        tbody.setAttribute("id", "products" + orders[x].id)
        selection.appendChild(thead);
        selection.appendChild(tbody);
    }
    getAllProducts();
}

async function displaySearch(string) {
    var table = document.getElementById("display");
    table.parentNode.removeChild(table);
    var newTable = document.createElement("table");
    newTable.setAttribute("id", "display");
    newTable.setAttribute("class", "table");

    var newThead = document.createElement("thead");
    newThead.setAttribute("class", "table-dark");

    var newTR = document.createElement("tr");
    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
    var td3 = document.createElement("td");
    var td4 = document.createElement("td");
    var td5 = document.createElement("td");

    td1.innerHTML = "Order ID";
    td2.innerHTML = "Created Date and Time"
    td3.innerHTML = "Product Name"
    td4.innerHTML = "Price"
    td5.innerHTML = "Manufacturer"

    newTR.appendChild(td1);
    newTR.appendChild(td2);
    newTR.appendChild(td3);
    newTR.appendChild(td4);
    newTR.appendChild(td5);
    newThead.appendChild(newTR);
    newTable.appendChild(newThead);
    let section = document.querySelector("section");
    section.appendChild(newTable);

}



async function loadAllProducts(products) {

    for (var x = 0; x < products.length; x++) {

        var tbody = document.querySelector("#products" + products[x].order.id)

        var tr = document.createElement("tr");
        var tdBuffer = document.createElement("td");
        var td1 = document.createElement("td");
        var td2 = document.createElement("td");
        var td3 = document.createElement("td");
        var td4 = document.createElement("td");

        var id = document.createTextNode(products[x].id)
        var name = document.createTextNode(products[x].name)
        var price = document.createTextNode("$" + products[x].price)
        var manufacturer = document.createTextNode(products[x].manufacturer)

        // td1.appendChild(id);
        td2.appendChild(name);
        td3.appendChild(price);
        td4.appendChild(manufacturer);

        tr.appendChild(tdBuffer);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tbody.appendChild(tr);
    }
}
