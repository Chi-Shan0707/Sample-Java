# Online Algorithm for  Online Decision Making
<P>
What is online decision making?<br> Suppose we have a decision that we’re trying to make, and we have n experts thathave conflicting opinions. Who do we trust, and which decision should we make? Here, in each successive trial, weknow the history up until the current trial (that is, we know nothing in trial 1, but in trial 2, we know the results fromtrial 1, etc.).

***

## Experts problems 



### Example
<p>
Suppose we have to make a decision on whether the weather will be rainy on a given day. We have n experts, and each day we keep track of which experts were wrong, and keep a running “loss tally” foreach expert (where 0 is a correct prediction and 1 is an incorrect prediction):
<p>

|expert id| day1 loss tally| day2 loss tally|
|------|----------------|----------------|
|1| 1  ， 1 | 1  ， 2 |
|2| 1  ， 1 | 0  ， 1 |
|3| 0  ， 0 | 1  ， 1 |
|4| 0  ， 0 | 0  ， 0 |
|5| 1  ， 1 | 1  ， 2 |

<P>
Here, each day, we want to make an “online” decision; that is, we only know the results from previous daysand the loss tallies up until now. After each day, the losses are revealed and we update the tallies.
<P>

***

### Definition
<br>

We have experts $E_1,...,E_n$  and days  $1,...,T$
<br>
On each day $t \in \{1,...,n\}$<br>
1. Each expert makes a prediction.
2. We choose an expert $E_{id[t]}$ to follow(choose an answer from their predictions).
3. Then the real outcome is revealed, and $E_i$'s prediction incurs loss $\ell_{i}^{(t)}$.


Ideally, we want to compare   $ L = \sum_{t=1}^T \ell_{id[t]}^{(t)}$ and $\sum_{t=1}^T \min_{id=1}^{n} \{ \ell_{id}^{(t)} \}$  ;  the latter case is **if we magically know.** <br>
This is not a realistic goal. Going back to the rain example, suppose we have two experts, where one expert always predicts that it does not rain, and one expert always predicts that it does rain. In the optimal case, we magically know whether it rains or not, and our loss is 0. However, more realistically, if it rains 50% of the time, no matter what we do, we incur a loss of approximately $\frac{T}{2}$; we expect to be wrong half of the time, as we can't possibly predict a uniformly random outcome.

As such, we will settle for something **slightly weaker** , i.e. compare our $$L$$ to $$\min_{id} \sum_{t=1}^T \ell_{id}^{(t)}$$. That is, we compare our actual loss to the case where we look back and only **follow one best expert throughout**. We further define $Regret$

$$R := L - \min_{id} \sum_{t=1}^T \ell_{id}^{(t)} = L - L^*$$
<br>

- There could be multiple actions that we could take (not just binary as we have in the previous example).<br>
- The losses can be some number $\ell_{j}^{(t)} \in [0,1]$. Here, all that is needed is that the loss is bounded; we can just rescale to [0,1] anyways.<br>
- We don't assume that past prediction-performances predict future performance.<br>
- Maybe all experts are wrong.
- In the traditional mode, you can only follow one expert on each day.
- The choice can be discrete, like "Yes/No", or can be continuous, like $f(x)\in C_[0,1]$

***

### Basic Strategies and Algorithms 

```
We have binary actions(yes or no) -> so the result is whether "correct" or "wrong"
The loss  hase been rescaled to [0,1]
```

Three trivial strategies:<br>
1. **Scenario 1 : Follow a fixed expert for all $T$ rounds**<br>
— trivial bound: $R \le T$ (each round's loss is at most 1).

2. **Scenario 2: Randomly choose an expert to follow each round** <br>
— the expected loss equals the average expert loss; this provides no strong worst-case regret guarantee.

3. **Scenario 3: Choose the best expert so far to follow**<br>
 We call it as *follow the leader* on the **loss tally** <br>
— quite reasonable?<br>

Let's consider two special scenarios.<br>

3. **Scenario 4 : There exists an expert who is always correct.**<br> 
— use the elimination/majority approach: at each round pick the option favored by the majority of experts who have not yet made any mistake (eliminate experts when they err). So $R \le log_2(n)$

4. **Scenario 5 : There exists an expert with at most $m$ mistakes overall.**<br>
— use the previous algo? 



***
**Multiplicative Weights Algorithm**<br>
$\rightarrow$ *Weghted majority algorithm*
- Initalized weights $w_1^{(1)}=...=w_n^{(1)}=1$ for each expert
- For $t=1,...,T$ ：
1. Output **weighted majority**
2. Set the weights for $t+1$:

```
let loss = 0 or 1, so loss_tally is equivalent to mistake_number
```
$$
w_i^{(t+1)} := \begin{cases}
(1-\epsilon)\,w_i^{(t)}, & \text{if expert }i\text{ errs at round }t,\\
w_i^{(t)}, & \text{if expert }i\text{ is correct at round }t.
\end{cases}
$$

> **Fact :**<br> Denote $W^{(t)} $= total weight of experts at time $t$<br>Suppose WM-algo made a mistake at time t, then $W^{(t+1)} \le (1-\frac{\epsilon}{2}) W^{(t)}$

Let $M$ denote the number of mistakes made by this algo. So we have<br>
$$
W^{(T+1)} \le n\left(1-\tfrac{\epsilon}{2}\right)^M
$$

Let $OPT$ denote the minimal number of mistakes made by any expert. Let that expert be $E_{id}$. Then its weight at time $t$ satisfies

$$
w_{id}^{(t)} \ge (1-\epsilon)^{OPT},\quad \forall t.
$$

Hence,

$$
W^{(t)} \ge w_{id}^{(t)} \ge (1-\epsilon)^{OPT},\quad \forall t.
$$

> **Note :**<br>In Weighted-Majority-With-$\epsilon$ algo, <br>we don't assume there is an expert who makes $\le m$ mistakes, and we don't know who is $E_{id}$ in advance —— it's just hindsight<br>

At time $T+1$ we get
$$
(1-\epsilon)^{OPT} \le W^{(T+1)} \le n\left(1-\tfrac{\epsilon}{2}\right)^{M}.
$$
Taking natural logarithms and rearranging yields
$$
M \le \frac{\ln(1-\epsilon)}{\ln(1-\tfrac{\epsilon}{2})}\,OPT + \frac{\ln n}{-\ln(1-\tfrac{\epsilon}{2})}.
$$
For $0<\epsilon\le\tfrac{1}{2}$ we can bound the logarithms to get
$$
M \le \frac{2}{\epsilon}OPT + \frac{2}{\epsilon}\ln n = \frac{2}{\epsilon}(OPT + \ln n).
$$
Thus the number of mistakes made by the algorithm is at most $\dfrac{2}{\epsilon}(OPT+\ln n)$.

***
<p>
That's not enough.<p>

**We have an adversary that can screw us over depending on what strategy we use.**

<p>
That is, the adversary knows everything about the algorithm and strategy, and can act accordingly to give us a worst-case outcome, which we’re trying to analyze. However, onecrucial limitation is that the adversary cannot possibly know what the output of randomness is—that is, randomchoices cannot be predicted by the adversary.
<P>

1. For **Scenario 1**, we can easily make $R=T$ .
2. For **Scenario 2**, we can calculate.

We will prove an upper bound on $\mathbb{E}[R]$, that is, $\mathbb{E}[R] \leq \left(1 - \frac{1}{n}\right)T$. If we pick a uniformly random expert to follow, each day we have an expected loss of $\frac{1}{n} \sum_{i=1}^n \ell_i^{(t)}$. This means that our total expected loss is

$$
\mathbb{E}[L] = \sum_{t=1}^T \left( \frac{1}{n} \sum_{i=1}^n \ell_i^{(t)} \right).
$$

We further know that one of these $i$'s is actually the best person of the day, so we can split this up:

$$
\mathbb{E}[L] = \sum_{t=1}^T \left( \frac{\min_{id} \ell_{id}^{(t)} }{n}+ \frac{1}{n} \sum_{j \ne id} \ell_j^{(t)} \right) \le \sum_{t=1}^T \left( \min_{id} \ell_{id}^{(t)} + \frac{1}{n} \sum_{j \ne id} \ell_j^{(t)} \right) 
$$

Firstly, we can see that $\sum_{t=1}^T \min_i \ell_i^{(t)} \leq L^*$; this will usually be better than our target $L^*$, as we have the choice of which best expert to choose on each day. Further, since each loss is upper bounded by 1, and we have $n-1$ other experts in the inner summation, the second term $\frac{1}{n} \sum_{j \ne i} \ell_j^{(t)} \leq \frac{n-1}{n}$.

$$
\mathbb{E}[L] \leq L^* + \sum_{t=1}^T \frac{n-1}{n} = L^* + \left(1 - \frac{1}{n}\right)T.
$$

As such, we have the following upper bound for our expected regret $\mathbb{E}[R]$:

$$
\mathbb{E}[R] = \mathbb{E}[L] - L^* \leq \left(1 - \frac{1}{n}\right)T.
$$


3. For **Scenario 3** (follow-the-leader-on-the-loss-tally), an adversary can force regret $ R \ge(1-\frac{1}{n})T $ by rotating the "best-so-far" and making it wrong each round:

We can construct $n=3$

| $t$ | reality: $y^{(t)}$ | Expert1 tally | Expert2 tally | Expert3 tally | Algo chooses | Correct? |
|-----|---------------------|---------------|---------------|---------------|--------------|----------|
| 1   | 0                   | 0             | 1/3           | 2/3           | Expert1      | ❌       |
| 2   | 1                   | 1             | 1/3           | 2/3           | Expert2      | ❌       |
| 3   | 0                   | 1             | 4/3           | 2/3           | Expert3      | ❌       |
| 4   | 1                   | 1             | 4/3           | 5/3           | Expert1      | ❌       |
| 5   | 0                   | 2             | 4/3           | 5/3           | Expert2      | ❌       |
| 6   | 1                   | 2             | 7/3           | 5/3           | Expert3      | ❌       |

- Algorithm total mistakes: $M \approx T$  
- Optimal expert mistakes: $L^* \approx \frac{T}{3}$ (each expert makes mistakes in their turn)  
- Regret: $R \approx T - \frac{T}{3} = \frac{2}{3}T \geq \left(1 - \frac{1}{n}\right)T$

4. For **scenario 4**, it's easy to make $R \ge log_2(n)$.
5. For **scenario 5**, 



We can construct $n=2$ $\epsilon = \frac{1}{2}$

|  $ t $  |  realality:$ y^{(t)} $  |  $ w_{1,t} $  |  $ w_{2,t} $  | Alg. Chooses | Correct? |  
|------|------------|------------|------------|--------------|----------|  
| 1    | 0          | 1.0        | 1.0        | Expert 2     | ❌       |  
| 2    | 1          | 1.0        | 0.5        | Expert 1     | ❌       |  
| 3    | 0          | 0.5        | 0.5        | Expert 2     | ❌       |  
| 4    | 1          | 0.5        | 0.25       | Expert 1     | ❌       |  
| 5    | 1          | 0.25       | 0.25       | Expert 2     | ❌       |  
| 6    | 0          | 0.25       | 0.125      | Expert 1     | ✅       |  



- **Algorithm total mistakes**: $M = 5$  
- **Optimal expert (Expert 1) mistakes**: $L^* = 2$ (errors at $t=2,4$)  
- **Regret**: $R = M - L^* = 5 - 2 = 3 $  


> **Theorem :**<br> Any deterministic algorithm can't beat 2!

$$
\forall  algo \in \set{\text{deterministic algorithms}}, \exists \text{adversary such that } L \geq (2-O \left (1)\right )L^*
$$

***

### Improve by Randomizing


**Randomized Weighted Majority (w/ parameter $\epsilon$)**

- Initialize weights $w_1^{(0)}=\cdots=w_n^{(0)}=1$.
- For $t=1,\dots,T$:
  1. Let $W^{(t)}=\sum_{i=1}^n w_i^{(t)}$.
  2. Randomly predict bit $b\in\{0,1\}$ with probability
	  $$\Pr[b]=\frac{1}{W^{(t)}}\sum_{i=1}^n w_i^{(t)}\cdot\mathbf{1}[\text{expert }i\text{ predicts }b].$$
  3. After the true outcome is revealed, update each expert's weight:
	 $$w_{i,t+1} := \begin{cases}(1-\epsilon)\,w_{i,t}, & \text{if expert }i\text{ errs at round }t,\\w_{i,t}, & \text{if expert }i\text{ is correct at round }t.\end{cases}
$$

> **Remarks:** the adversary cannot predict the algorithm's internal randomness, so randomization prevents certain worst-case cycles encountered by deterministic "follow-the-leader" strategies.

> **Theorem.** Let $M$ be the number of mistakes made by Randomized Weighted Majority. Then
$$
\mathbb{E}[M] \le (1+\epsilon)\,OPT + \frac{\log_2 n}{\epsilon}.
$$

Sketch: mirror the multiplicative-weights potential argument. In expectation, when the algorithm's randomized prediction errs the total weight decreases by a constant multiplicative factor (about $1-\epsilon/2$), while the best expert's weight decreases by at most $(1-\epsilon)^{OPT}$. Rearranging yields the displayed bound.

Pick $\epsilon=\sqrt{\dfrac{\log_2 n}{T}}$. Then
$$
\mathbb{E}[M] \le OPT + 2\sqrt{T\log_2 n}.
$$
Dividing by $T$ gives
$$
\frac{\mathbb{E}[M]}{T} \le \frac{OPT}{T} + 2\sqrt{\frac{\log_2 n}{T}}\xrightarrow[T\to\infty]{}\frac{OPT}{T}.
$$
Hence when $OPT=o(T)$ the algorithm is "no-regret" — average regret vanishes as $T\to\infty$.

***

### Hedge
*a special case of Multiplicative Weights Update*

`Losses: `$\ell_i^{(t)} \in [0,1] $ <br>
`Prediction: discrete`

Define

$$
x_i^{(t)}=\frac{w_i^{(t)}}{W^{(t)}},\qquad W^{(t)}=\sum_{j=1}^n w_j^{(t)}.
$$

Per-round expected loss:
$$
L_t=\sum_{i=1}^n x_i^{(t)}\ell_i^{(t)}.
$$

Total expected loss:
$$
L=\sum_{t=1}^T L_t.
$$


For $\epsilon \in(0,\tfrac12]$:

$$
w_i^{(t+1)}=w_i^{(t)}(1-\epsilon)^{\ell_i^{(t)}}.
$$

Let
$$
id := \arg\min_{i} \sum_{t=1}^T \ell_i^{(t)},\qquad
L^* := \sum_{t=1}^T \ell_{id}^{(t)}.
$$

**Lower bound (best expert)**
$$
W^{(T+1)} \ge w_{id}^{(T+1)} = (1-\epsilon)^{L^*}.
$$

**Upper bound (from expected loss)**

> **Lemma (Bernoulli):**
>
> $$(1+x)^z \le 1 + xz \quad (x \ge -1,\ z\in(0,1)).$$

Hence

$$
W^{(t+1)} = \sum_{i=1}^n w_i^{(t)}(1-\epsilon)^{\ell_i^{(t)}} \le \sum_{i=1}^n w_i^{(t)}(1-\epsilon\,\ell_i^{(t)}) = W^{(t)}(1-\epsilon L_t).
$$

Iterating gives

$$
W^{(T+1)} \le W^{(1)}\prod_{t=1}^T (1-\epsilon L_t) = n\prod_{t=1}^T(1-\epsilon L_t).
$$

Combining the two bounds yields

$$
(1-\epsilon)^{L^*} \le n\prod_{t=1}^T(1-\epsilon L_t).
$$

Taking natural logarithms:

$$
L^*\ln(1-\epsilon) \le \ln n + \sum_{t=1}^T \ln(1-\epsilon L_t).
$$

Using $\ln(1-z) \le -z$ and $\ln(1-z) \ge -z - z^2$ for small $z$, we obtain

$$
L^*(-\epsilon - \epsilon^2) \le \ln n - \epsilon \sum_{t=1}^T L_t.
$$

Rearranging (with $L=\sum_{t=1}^T L_t$):

$$
L - L^* \le \frac{\ln n}{\epsilon } + \epsilon L^*.
$$

Since $L^* \le T$ , this implies

$$
L - L^* \le \frac{\ln n}{\epsilon} + \epsilon T.
$$

Optimizing $\epsilon = \sqrt{\dfrac{\ln n}{T}}$ gives

$$
R := L - L^* \le 2\sqrt{T\ln n}.
$$

***

## Zero-Sum Problem

### 1. Basic Setup

#### Core Definitions

- **Payoff Matrix**: Let  $A \in \mathbb{R}^{m \times n}$  be the payoff matrix for Player 1 (P1). For pure strategies  $i$  (P1) and  $j$  (P2), P1’s gain =  $A_{ij}$ , P2’s gain =  $-A_{ij}$  (zero-sum property).

- **Mixed Strategies**: Probability vectors (in simplex  $\Delta$ ,  $x_i \geq 0, \sum x_i = 1$ ):

    - P1’s strategy:  $x^{(t)} = (x_1^{(t)}, ..., x_m^{(t)}) \in \Delta_m$  (prob of choosing pure strategy  $i$  at step  $t$ )

    - P2’s strategy:  $y^{(t)} = (y_1^{(t)}, ..., y_n^{(t)}) \in \Delta_n$  (prob of choosing pure strategy  $j$  at step  $t$ )

- **Loss Vectors**:

    - P1’s loss vector:  $\ell_1^{(t)} = -A y^{(t)}$ 

    - P2’s loss vector:  $\ell_2^{(t)} = A^T x^{(t)}$ 

- **Expected Payoff (Step** $t$  **)**: <br> $(x^{(t)})^T A y^{(t)} = \sum_{i=1}^m \sum_{j=1}^n x_i^{(t)} A_{ij} y_j^{(t)}$ 

### 2. Algorithm Interaction (Both Players Use Multiplicative Weights)

#### Steps for  $t = 1, ..., T$ 

1. **Initialize Weights**:  $w_1^{(1)} = \mathbf{1} \in \mathbb{R}^m$ ,  $w_2^{(1)} = \mathbf{1} \in \mathbb{R}^n$ 

2. **Generate Strategies**:

    -  $x_i^{(t)} = \frac{w_{1,i}^{(t)}}{\sum_{k=1}^m w_{1,k}^{(t)}}$ 

    -  $y_j^{(t)} = \frac{w_{2,j}^{(t)}}{\sum_{k=1}^n w_{2,k}^{(t)}}$ 

3. **Observe Losses**: P1 gets  $\ell_1^{(t)}$ , P2 gets  $\ell_2^{(t)}$ 

4. **Update Weights**:

    -  $w_{1,i}^{(t+1)} = w_{1,i}^{(t)} \cdot (1-\epsilon)^{\ell_{1,i}^{(t)}}$ 

    -  $w_{2,j}^{(t+1)} = w_{2,j}^{(t)} \cdot (1-\epsilon)^{\ell_{2,j}^{(t)}}$ 

### 3. Regret Bounds

#### Regret Definition

- P1’s regret:  $R_1^T = \sum_{t=1}^T (x^{(t)})^T \ell_1^{(t)} - \min_{x \in \Delta_m} \sum_{t=1}^T x^T \ell_1^{(t)}$ 

- P2’s regret:  $R_2^T = \sum_{t=1}^T (y^{(t)})^T \ell_2^{(t)} - \min_{y \in \Delta_n} \sum_{t=1}^T y^T \ell_2^{(t)}$ 

#### Bounds (Optimal  $\epsilon = \sqrt{\frac{\ln n}{T}}$ )


$$
R_1^T = O\left(\sqrt{T \ln m}\right), \quad R_2^T = O\left(\sqrt{T \ln n}\right)
$$

Average regret:  $\frac{R_1^T}{T} = O\left(\sqrt{\frac{\ln m}{T}}\right) \to 0$  as  $T \to \infty$ 

### 4. Minimax Theorem Proof

#### Step 1: Average Strategies

Define time-averaged strategies (still in  $\Delta$ ):


$$
\overline{x} = \frac{1}{T}\sum_{t=1}^T x^{(t)}, \quad \overline{y} = \frac{1}{T}\sum_{t=1}^T y^{(t)}
$$

#### Step 2: Rewrite Regret Bounds

- P1’s bound (after simplification):

 $\max_{x \in \Delta_m} x^T A \overline{y} - \frac{1}{T}\sum_{t=1}^T (x^{(t)})^T A y^{(t)} = O\left(\sqrt{\frac{\ln m}{T}}\right)$ 

- P2’s bound (after simplification):

 $\frac{1}{T}\sum_{t=1}^T (x^{(t)})^T A y^{(t)} - \min_{y \in \Delta_n} \overline{x}^T A y = O\left(\sqrt{\frac{\ln n}{T}}\right)$ 

### Step 3: Combine Bounds & Weak Duality

Add the two bounds:

$$
\max_{x} x^T A \overline{y} - \min_{y} \overline{x}^T A y = O\left(\sqrt{\frac{\ln \max(m,n)}{T}}\right)
$$


By **weak duality**:  $\min_y \max_x x^T A y \geq \max_x \min_y x^T A y \geq 0$ 

### Step 4: Strong Duality (Minimax Theorem)

The difference  $\min_y \max_x x^T A y - \max_x \min_y x^T A y$  is independent of  $T$ . As  $T \to \infty$ , the right-hand side tends to 0. Thus:

$$

\min_{y \in \Delta_n} \max_{x \in \Delta_m} x^T A y = \max_{x \in \Delta_m} \min_{y \in \Delta_n} x^T A y

$$

### 5. Key Conclusion

- **Von Neumann’s Minimax Theorem**: For two-player zero-sum games, there exist optimal mixed strategies  $x^* \in \Delta_m$  and  $y^* \in \Delta_n$  such that the game has a unique "value"  $v = (x^*)^T A y^*$ .

- **Algorithm Value**: The multiplicative weights algorithm learns near-optimal strategies online (no prior knowledge of  $A$ ), with average regret vanishing as  $T \to \infty$ .




***

[Record](https://www.bilibili.com/video/BV1mu1CYRESn?spm_id_from=333.788.videopod.sections&vd_source=de61571668b4f9b7a6cbfb72c2ad3a42&p=17)