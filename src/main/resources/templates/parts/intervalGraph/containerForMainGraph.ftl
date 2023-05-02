<div>
    <table id="mainIntervalGraph" border="1">
        <tr>
            <th>Graph</th>
            <th>Vertices</th>
            <th>Min weight</th>
            <th>Max weight</th>
        </tr>
    </table>
</div>

<script>
    let g = JSON.parse(${intervalGraph});
    let n = g.vertices.length;
    // граф готов к тому, чтобы получать из него краткую информацию

    //создаем новую строку для таблицы
    let newRow = document.createElement("tr");

    //создаем ячейки для новой строки и заполняем их значениями
    let graphCell = document.createElement("td");
    graphCell.addEventListener('click', function() {
        console.log("pushed button main graph " + ${intervalGraph});
        fetch("/interval/mainGraph");
        window.location.reload();
    });
    graphCell.addEventListener("mouseover", function() {
        graphCell.style.backgroundColor = "peachpuff";
    });
    graphCell.addEventListener("mouseout", function() {
        graphCell.style.backgroundColor = "inherit";
    });
    graphCell.textContent = 'Interval graph';

    newRow.appendChild(graphCell);

    let verticesCell = document.createElement("td");
    verticesCell.innerText = ""+n;
    newRow.appendChild(verticesCell);

    let minWeightCell = document.createElement("td");
    minWeightCell.innerText = ""+g.minWeight;
    newRow.appendChild(minWeightCell);

    let maxWeightCell = document.createElement("td");
    maxWeightCell.innerText = ""+g.maxWeight;
    newRow.appendChild(maxWeightCell);

    //добавляем новую строку в конец таблицы
    let table = document.getElementById("mainIntervalGraph");
    table.appendChild(newRow);
</script>