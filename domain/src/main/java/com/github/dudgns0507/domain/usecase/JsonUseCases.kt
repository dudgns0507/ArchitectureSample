package com.github.dudgns0507.domain.usecase

data class JsonUseCases(
    val getPostUseCase: GetPostUseCase,
    val getPostsUseCase: GetPostsUseCase,
    val getCommentsUseCase: GetCommentsUseCase,
    val patchPostUseCase: PatchPostUseCase,
    val deletePostUseCase: DeletePostUseCase
)