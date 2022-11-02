#include <iterator>
#include <list>
#include <vector>

template <class T>
class VectorList {
private:
    using VectT = std::vector<T>;
    using ListT = std::list<VectT>;
    using list_it = typename ListT::const_iterator;
    using vect_it = typename VectT::const_iterator;

    ListT data_;

public:
    VectorList() = default;
    VectorList(VectorList const&) = default;
    VectorList(VectorList&&) noexcept = default;

    VectorList& operator=(VectorList&&) noexcept = default;
    VectorList& operator=(VectorList const&) = default;

    template <class It>
    void append(It p, It q) {
        if (p != q) data_.push_back(VectT(p, q));
    }
    bool empty() const { return size() == 0; }
    size_t size() const {
        size_t count = 0;
        for (const auto& d : data_) {
            count += d.size();
        }
        return count;
    }

    struct const_iterator : std::iterator<std::bidirectional_iterator_tag, const T> {
    public:
        const_iterator() = default;
        const_iterator(const_iterator const& other) = default;
        const_iterator(ListT const* const current, list_it const& list, vect_it const& vect)
            : itData(current), itListConst(list), itVectConst(vect) {}
        const_iterator& operator=(const const_iterator& other) = default;
        ~const_iterator() = default;

        const T& operator*() const { return *itVectConst; }
        const T* operator->() const { return &*itVectConst; }

        const_iterator& operator++() {
            ++itVectConst;
            if (itVectConst == itListConst->end() &&
                std::next(itListConst) != itData->end()) {
                ++itListConst;
                itVectConst = itListConst->cbegin();
            }
            return *this;
        }

        const_iterator operator++(int) {
            const_iterator tmp = *this;
            ++* this;
            return tmp;
        }

        const_iterator& operator--() {
            if (itListConst == itData->end() || itVectConst == itListConst->begin()) {
                if (itListConst != itData->begin()) {
                    --itListConst;
                    itVectConst = itListConst->end();
                }
            }
            --itVectConst;
            return *this;
        }
        const_iterator operator--(int) {
            auto temp = *this;
            --* this;
            return temp;
        }

        // нельзя сравнивать итераторы на разные вектора, поэтому сначала проверяем равенство списков
        bool operator==(const const_iterator& other) const {
            return itListConst == other.itListConst &&
                itVectConst == other.itVectConst;
        }
        bool operator!=(const const_iterator& other) const {
            return !(*this == other);
        }

    private:
        list_it itListConst;
        vect_it itVectConst;
        ListT const* itData = 0;
    };

    const_iterator begin() const {
        if (!empty())
            return const_iterator(&data_, data_.cbegin(), data_.cbegin()->cbegin());
        else
            return const_iterator();
    }
    const_iterator end() const {
        if (!empty())
            return const_iterator(&data_, std::prev(data_.end()),
                std::prev(data_.end())->end());
        else
            return const_iterator();
    }

    using const_reverse_iterator = std::reverse_iterator<const_iterator>;
    const_reverse_iterator rbegin() const {
        return const_reverse_iterator(end());
    }
    const_reverse_iterator rend() const {
        return const_reverse_iterator(begin());
    }
};
