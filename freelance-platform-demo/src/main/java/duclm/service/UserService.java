package duclm.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import duclm.domain.User;

public interface UserService {

	<S extends User> List<S> findAll(Example<S> example, Sort sort);

	<S extends User> List<S> findAll(Example<S> example);

	User getReferenceById(Long id);

	User getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends User> entities);

	User getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends User, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(User entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends User> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<User> entities);

	<S extends User> long count(Example<S> example);

	void deleteInBatch(Iterable<User> entities);

	<S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends User> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends User> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends User> List<S> saveAll(Iterable<S> entities);

	Optional<User> findById(Long id);

	List<User> findAllById(Iterable<Long> ids);

	List<User> findAll(Sort sort);

	Page<User> findAll(Pageable pageable);

	List<User> findAll();

	<S extends User> Optional<S> findOne(Example<S> example);

	<S extends User> S save(S entity);

	User login(String email, String password);
	
}
