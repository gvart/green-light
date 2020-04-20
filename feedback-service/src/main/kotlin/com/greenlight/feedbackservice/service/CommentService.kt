package com.greenlight.feedbackservice.service

import com.greenlight.common.security.service.SecurityService
import com.greenlight.common.service.AbstractCoroutineReadWriteService
import com.greenlight.common.web.error.httperror.ConflictException
import com.greenlight.feedbackservice.domain.Comment
import com.greenlight.feedbackservice.repository.CommentRepository
import com.greenlight.feedbackservice.transfer.CommentRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.validation.Validator

@Service
class CommentService(
    private val securityService: SecurityService,
    private val repository: CommentRepository,
    validator: Validator
) : AbstractCoroutineReadWriteService<Comment, CommentRequest, Long>(validator) {

    companion object {
        private val logger = LoggerFactory.getLogger(CommentService::class.java)
    }

    suspend fun findAllByEventId(eventId: Long): Flow<Comment> {
        logger.debug("Request to find all comments by eventId")
        return repository.findAllByEventId(eventId).asFlow()
    }

    override suspend fun saveEntity(request: CommentRequest): Comment {
        val userId = securityService.extractUserId().awaitSingle()
        val userName = securityService.extractUsername().awaitSingle()
        val comment = Comment(
            userId = userId,
            userName = userName,
            eventId = request.eventId,
            content = request.content
        )

        return repository.save(comment).awaitSingle()
    }

    override suspend fun updateEntity(id: Long, request: CommentRequest): Comment {
        val comment = findOne(id)
        checkThatOwnerUpdateComment(comment)
        comment.content = request.content

        return repository.save(comment).awaitSingle()
    }

    private suspend fun checkThatOwnerUpdateComment(comment: Comment) {
        val userId = securityService.extractUserId().awaitSingle()
        if (comment.userId != userId) {
            throw ConflictException("User Id don't match to comment.")
        }
    }

    override suspend fun deleteEntity(id: Long) {
        val comment = findOne(id)
        checkThatOwnerUpdateComment(comment)
        repository.delete(comment).awaitSingle()
    }

    override fun findAllEntities(): Flow<Comment> {
        return repository.findAll().asFlow()
    }

    override suspend fun findOneEntity(id: Long): Comment {
        return repository.findById(id).awaitSingle()
    }

    override fun getLogger(): Logger {
        return logger
    }

}