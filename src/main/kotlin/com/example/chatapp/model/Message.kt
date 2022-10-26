package com.example.chatapp.model

import org.hibernate.Hibernate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "messages")
data class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val text: String,

    @Column(name = "sent_at")
    val sentAt: Long,

    @Column(name = "user_id")
    val userId: Long,

    // @ManyToOne()
    // @JoinColumn(
    //     name = "user_id",
    //     referencedColumnName = "id"
    // )
    // val user: User
) {

    constructor(text: String, sentAt: Long, userId: Long) : this(0L, text, sentAt, userId)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Message

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , text = $text , sentAt = $sentAt , userId = $userId )"
    }
}
