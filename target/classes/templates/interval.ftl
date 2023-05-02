<#import "/spring.ftl" as spring/>

<#include "parts/common/headerMenu.html">

<#include "parts/intervalGraph/dropdownButtonInterval.ftl">

<#include "parts/intervalGraph/intervalMenu.html">

<#include "parts/intervalGraph/intervalQuantityForm.ftl">

<p id="textInterval" class="textInfo">
    <#if intervalQuantity!=0>
        A graph with interval weights, the number of vertices = ${intervalQuantity}
        <#include "parts/intervalGraph/formForIntervalGraphInfo.ftl">
        <script src="/js/interval/functionsForDrawingIntervalGraphs.js"></script>
        <script>
            <#if intervalGraphToShow??>
            let g1, n1, vertices1, graph1, s;
            g1 = JSON.parse(${intervalGraphToShow});
            n1 = g1.vertices.length;
            if (n != undefined && n != 0) {
                vertices1 = generateVertices(n1, 680, 680);
                graph1 = new IntervalGraph(g1.probability, g1.id, g1.minWeight, g1.maxWeight);
                vertices1.forEach((vertex) => {
                    graph1.addVertex(vertex);
                });
                g1.edges.forEach((edge) => {
                    let e = new IntervalEdge(
                        vertices1[edge.a - 1],
                        vertices1[edge.b - 1],
                        edge.startWeight,
                        edge.endWeight);
                    graph1.addEdge(e);
                });
                drawIntervalGraph(graph1);
                s = graphToReadingFormat(graph1);
                document.getElementById("textGraph").value = s;
            }
            </#if>
        </script>
    <#else>
        To get started, generate a graph.
        <#include "parts/intervalGraph/formForIntervalGraphInfo.ftl">
    </#if>
</p>



<#if intervalQuantity==0>
    <script>
        document.getElementById("textGraph").value = "";
    </script>
</#if>


<script src="/js/common/script.js"></script>
<script src="/js/common/scriptSubmitForm.js"></script>
<script src="/js/common/scriptForMenu.js"></script>


</body>

</html>