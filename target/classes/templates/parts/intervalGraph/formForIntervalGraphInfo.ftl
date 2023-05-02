
<form class="container-for-graph-info">
    <div class="img-container">
        <canvas id="canvasForGraph" width="680px" height="680px" class="canvas"></canvas>
    </div>
    <div class="text-container">
        <#include "containerForMainGraph.ftl">
        <h4>Graph in text form</h4>
        <textarea class="textView" id="textGraph" readonly> </textarea>
        <h4>Prim decisions</h4>
        <div class="textview-for-interval-graph">
            <#include "containerForPrimDecisions.ftl">
        </div>
        <h4>Kruskal decisions</h4>
        <div class="textview-for-interval-graph">
            <#include "containerForKruskalDecisions.ftl">
        </div>
    </div>
</form>