<div class="container-for-graphs">
    <table id="kruskalDecisionsTable" border="1">
        <tr>
            <th>Decision</th>
            <th>Probability</th>
            <th>Min weight</th>
            <th>Max weight</th>
        </tr>
        <script>
            let kruskalGraphsDto = JSON.parse(${intervalKruskalGraphs});
            let kruskalDecisions = kruskalGraphsDto.decisions;

            kruskalDecisions.forEach((decision)=>{
                //создаем новую строку для таблицы
                let newRow = document.createElement("tr");

                //создаем ячейки для новой строки и заполняем их значениями
                let graphCell = document.createElement("td");

                graphCell.addEventListener('click', function() {
                    console.log("pushed button kruskal " + decision);
                    fetch('/interval/kruskal/'+decision.id);
                    window.location.reload();
                });
                graphCell.addEventListener("mouseover", function() {
                    graphCell.style.backgroundColor = "peachpuff";
                });
                graphCell.addEventListener("mouseout", function() {
                    graphCell.style.backgroundColor = "inherit";
                });
                graphCell.textContent = decision.id;

                newRow.appendChild(graphCell);

                let verticesCell = document.createElement("td");
                verticesCell.innerText = "" + decision.probability;
                newRow.appendChild(verticesCell);

                let minWeightCell = document.createElement("td");
                minWeightCell.innerText = "" + decision.minWeight;
                newRow.appendChild(minWeightCell);

                let maxWeightCell = document.createElement("td");
                maxWeightCell.innerText = "" + decision.maxWeight;
                newRow.appendChild(maxWeightCell);

                //добавляем новую строку в конец таблицы
                let table = document.getElementById("kruskalDecisionsTable");
                table.appendChild(newRow);
            })
        </script>
    </table>
</div>