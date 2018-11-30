package io.wooo.tensquare.qa.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.wooo.tensquare.common.entity.PageResult;
import io.wooo.tensquare.qa.entity.Problem;
import io.wooo.tensquare.qa.entity.QProblem;
import io.wooo.tensquare.qa.entity.QReply;
import io.wooo.tensquare.qa.repository.ProblemRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 4:56 PM
 * @description:
 */
@AllArgsConstructor
public class ProblemRepositoryImpl implements ProblemRepositoryCustom {

    private JPAQueryFactory jpaQueryFactory;

    @Override
    public PageResult<Problem> findByLabelIdAndNewlist(String labelId, Pageable pageable) {
        final QProblem problem = QProblem.problem;
        final QReply reply = QReply.reply;
        final List<Problem> problems = jpaQueryFactory.selectFrom(problem)
                .leftJoin(problem.reply, reply)
                .where(problem.labelId.eq(labelId))
                .orderBy(reply.createtime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        final PageResult<Problem> result = new PageResult<>();
        result.setRows(problems);
        result.setTotal(problems.size());
        return result;
    }

    @Override
    public PageResult<Problem> findByHotlist(String labelId, Pageable pageable) {
        final QProblem problem = QProblem.problem;
        final QReply reply = QReply.reply;
        final List<Problem> problems = jpaQueryFactory.selectFrom(problem)
                .leftJoin(problem.reply, reply)
                .where(problem.labelId.eq(labelId))
                .orderBy(problem.replyCount.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        final PageResult<Problem> result = new PageResult<>();
        result.setRows(problems);
        result.setTotal(problems.size());
        return result;
    }

    @Override
    public PageResult<Problem> findByWaitlist(String labelId, Pageable pageable) {
        final QProblem problem = QProblem.problem;
        final QReply reply = QReply.reply;
        final List<Problem> problems = jpaQueryFactory.selectFrom(problem)
                .leftJoin(problem.reply, reply)
                .where(problem.labelId.eq(labelId).and(problem.replyCount.eq(0L)))
                .orderBy(reply.createtime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        final PageResult<Problem> result = new PageResult<>();
        result.setRows(problems);
        result.setTotal(problems.size());
        return result;
    }
}