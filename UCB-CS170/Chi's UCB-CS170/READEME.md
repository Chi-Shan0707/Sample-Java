<<<<<<< HEAD
# Algorithm

1. Halt
2. Correctness
3. Efficiency

## NetworkFlow

1. augmenting path
2. residual graph

### Ford-Fulkerson

> Terminate? No, when irrational numbers!  
But when all edges are integers, it will terminate! -----integer discrete  
O((N+N)*MAX_FLOW),but input flow_capacity needs (log(max_flow)), so not poly

> min ST-CUT = max_flow , which shows strong duality  
when terminate, the graph shows ST-CUT: in the last iteration, s cannot reach t, all the vertices that s can reach comprise "S", the rest part is "T", and this is the min-STCUT

### Karp

### Dinic

### Lee Sidford

> O(m sqrt(n) log u)


=======
# Algorithm

1. Halt
2. Correctness
3. Efficiency

## NetworkFlow
1. augmenting path
2. residual graph
### Ford-Fulkerson
> Terminate? No, when irrational numbers!
> But when all edges are integers, it will terminate! -----integer discrete
> O((N+N)*MAX_FLOW),but input flow_capacity needs (log(max_flow)), so not poly

> min ST-CUT = max_flow , which shows strong duality
> when terminate, the graph shows ST-CUT: in the last iteration, s cannot reach t, all the vertices that s can reach comprise "S", the rest part is "T", and this is the min-STCUT
### Karp
### Dinic
### Lee Sidford
> O(m sqrt(n) log u)
>>>>>>> 755451455680d3df3c743e7f82643ee18b97d39e
