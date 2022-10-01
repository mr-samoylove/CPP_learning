#include <vector>
#include <numeric>

template <typename IT, typename lambdaUnary, typename lambdaBinary>
auto map_reduce(IT begin, IT end, lambdaUnary f1, lambdaBinary f2, size_t threads_amount) -> decltype(f2(f1(*begin), f1(*begin)))
{
	using ResultOfLambdaBinary = decltype(f2(f1(*begin), f1(*begin)));
	using VectorThread = std::vector<std::thread>;

	size_t tasks_per_thread = std::distance(begin, end) / threads_amount;

	VectorThread vt(threads_amount);
	std::vector<ResultOfLambdaBinary> v_res(threads_amount);

	auto from = begin;
	auto to = std::next(begin, tasks_per_thread);

	auto process = [&f1, &f2, &v_res](IT from, IT to, size_t v_index) {
		auto res = f1(*from);
		while (++from != to) {
			res = f2(res, f1(*from));
		}

		v_res[v_index] = res;
	};

	int i = 0;
	while (threads_amount--) {
		
		vt.emplace_back(std::thread(process, from, to, i++));

		if (to != end)
		{
			from = to;
			to = (threads_amount == 1) ? end : std::next(from, tasks_per_thread);
		}
	}
	for (auto&& it : vt)
		if (it.joinable()) it.join();

	return std::accumulate(++v_res.begin(), v_res.end(), v_res.front(), f2);
}
