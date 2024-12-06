package com.tdav.services.sandbox.services;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tdav.services.sandbox.dtos.BlogPostDTO;
import com.tdav.services.sandbox.entities.BlogPost;
import com.tdav.services.sandbox.handlers.InvalidDataException;
import com.tdav.services.sandbox.handlers.ResourceNotFoundException;
import com.tdav.services.sandbox.repositories.BlogPostRepository;
import com.tdav.services.sandbox.repositories.specifications.GeneralEqualitySpec;

@Service
public class BlogPostService {

  private BlogPostRepository blogPostRepo;

  public BlogPostService(BlogPostRepository blogPostRepo) {
    this.blogPostRepo = blogPostRepo;
  }

  public List<BlogPostDTO> getAllBlogPosts() {
    return blogPostRepo.findAll()
        .stream()
        .map(BlogPostDTO::new)
        .collect(Collectors.toList());
  }

  public Page<BlogPostDTO> getBlogPostPage(BlogPost model, int page, int size, String sortBy, String sortDir) {
    Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
    Pageable pageable = PageRequest.of(page, size, sort);
    Specification<BlogPost> specification = GeneralEqualitySpec.byCriteria(model);
    return blogPostRepo.findAll(specification, pageable).map(BlogPostDTO::fromEntity);
  }

  public BlogPost getBlogPostById(UUID id) {
    return blogPostRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Blog post with ID: " + id.toString() + " not found."));
  }

  public BlogPost getBlogPostBySlug(String slug) {
    return blogPostRepo.findOneBySlug(slug)
        .orElseThrow(() -> new ResourceNotFoundException("Blog post with slug: " + slug + " not found."));
  }

  public Long incrementViewCountBySlug(String slug) {
    BlogPost blogPost = getBlogPostBySlug(slug);
    blogPost.setViewCount(blogPost.getViewCount() + 1);
    return blogPostRepo.save(blogPost).getViewCount();
  }

  public Long incrementViewCountById(UUID id) {
    BlogPost blogPost = getBlogPostById(id);
    blogPost.setViewCount(blogPost.getViewCount() + 1);
    return blogPostRepo.save(blogPost).getViewCount();
  }

  public BlogPost saveBlogPost(BlogPost blogPost) {
    if (blogPost.getId() != null) {
      throw new InvalidDataException(
          "Cannot persist a new blog post with an existing ID. Use the update method instead.");
    }
    blogPost.setSlug(getSlug(blogPost.getTitle()));
    return blogPostRepo.save(blogPost);
  }

  public BlogPost updateBlogPost(BlogPost blogPost, UUID id) {
    if (!blogPostRepo.existsById(id)) {
      throw new ResourceNotFoundException("Project with ID: " + id.toString() + " not found.");
    } else if (!blogPost.getId().equals(id)) {
      throw new InvalidDataException("Project ID in request and body do not match.");
    }
    blogPost.setSlug(getSlug(blogPost.getTitle()));
    return blogPostRepo.save(blogPost);
  }

  public void deleteBlogPost(BlogPost blogPost, UUID id) {
    if (!blogPostRepo.existsById(id)) {
      throw new ResourceNotFoundException("Project with ID: " + id.toString() + " not found.");
    } else if (!blogPost.getId().equals(id)) {
      throw new InvalidDataException("Project ID in request and body do not match.");
    }
    blogPostRepo.delete(blogPost);
  }

  private String getSlug(String title) {
    String titleSlug = title;
    if (titleSlug != null) {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      titleSlug = titleSlug.toLowerCase();
      titleSlug = Normalizer.normalize(titleSlug, Normalizer.Form.NFD);
      titleSlug = titleSlug.replaceAll("[^a-z0-9\\s]", "").replaceAll("\\s+", "-");
      titleSlug = titleSlug.replaceAll("^-|-$", "");
      titleSlug = titleSlug + "-" + formatter.format(new Date()).replaceAll("[ :]", "-");
    }
    return titleSlug;
  }

}
