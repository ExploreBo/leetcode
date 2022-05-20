import random
with open("./README.md", "r") as f:
	lines = f.readlines()
	candidates = []
	for line in lines:
		if "](https://" in line:
			candidates.append(line)
	print(candidates[random.randint(0, len(candidates) - 1)])