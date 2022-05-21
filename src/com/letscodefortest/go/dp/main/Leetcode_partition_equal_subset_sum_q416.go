package main

import (
	"fmt"
)

/*
	Runtime: 16 ms, faster than 87.55% of Go online submissions for Partition Equal Subset Sum.
	Memory Usage: 2.6 MB, less than 91.44% of Go online submissions for Partition Equal Subset Sum.
*/
func canPartition(nums []int) bool {
	var sum int
	for _, num := range nums {
		sum += num
	}
	if sum%2 == 1 {
		return false
	}
	halfSum := sum / 2

	memo := make([]bool, halfSum+1)
	memo[0] = true

	for _, curr := range nums {
		for i := halfSum; i >= curr; i-- {
			memo[i] = memo[i] || memo[i-curr]
		}
	}

	return memo[halfSum]
}

func main() {
	fmt.Println(canPartition([]int{1, 5, 11, 5}))
}
