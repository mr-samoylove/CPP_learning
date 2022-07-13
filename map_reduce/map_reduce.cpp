//задача:
//Результат вызова map_reduce
//auto res = map_reduce(p, q, f1, f2, num);
//должен быть эквивалентен результату следующего кода, только выполнение этого кода должно быть разбито на num потоков. :
//
//auto res = f1(*p);
//while (++p != q)
//res = f2(res, f1(*p));

#include <vector>
#include <future>

template <typename IT, typename lambdaUnary, typename lambdaBinary>
auto map_reduce_help(IT begin, IT end, lambdaUnary f1, lambdaBinary f2) -> decltype(f2(f1(*begin), f1(*begin))) {
    auto res = f1(*begin);
    while (++begin != end) {
        res = f2(res, f1(*begin));
    }
    return res;
}


template <typename IT, typename lambdaUnary, typename lambdaBinary>
auto map_reduce(IT begin, IT end, lambdaUnary f1, lambdaBinary f2, size_t threads_amount) -> decltype(f2(f1(*begin), f1(*begin)))
{
    using ResultOfLambdaBinary = decltype(f2(f1(*begin), f1(*begin)));
    using VectorFuture = std::vector<std::future<ResultOfLambdaBinary>>;

    size_t tasks_per_thread = std::distance(begin, end) / threads_amount;

    VectorFuture v;
    auto from = begin;
    auto to = std::next(begin, tasks_per_thread);

    while (threads_amount--) {
        v.emplace_back(std::async(std::launch::async, map_reduce_help<IT, lambdaUnary, lambdaBinary>, from, to, f1, f2));

        if (to == end) break;
        else
        {
            from = to;
            to = (threads_amount == 1) ? end : std::next(from, tasks_per_thread);
        }
    }

    return map_reduce_help(
        v.begin(),
        v.end(),
        [](decltype(*v.begin()) f) { return f.get(); },
        f2);
}
