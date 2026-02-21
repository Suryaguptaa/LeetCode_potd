#!/usr/bin/env python3
"""
Script to fetch LeetCode stats and update README.md
"""

import os
import re
import requests
from datetime import datetime

def get_leetcode_stats(username):
    """Fetch user stats from LeetCode GraphQL API"""
    url = "https://leetcode.com/graphql"
    
    query = """
    query getUserProfile($username: String!) {
        matchedUser(username: $username) {
            submitStats {
                acSubmissionNum {
                    difficulty
                    count
                    submissions
                }
            }
            profile {
                ranking
            }
        }
        userContestRanking(username: $username) {
            attendedContestsCount
            rating
            globalRanking
        }
    }
    """
    
    headers = {
        "Content-Type": "application/json",
        "Referer": "https://leetcode.com"
    }
    
    try:
        response = requests.post(
            url,
            json={"query": query, "variables": {"username": username}},
            headers=headers,
            timeout=10
        )
        response.raise_for_status()
        return response.json()
    except Exception as e:
        print(f"Error fetching LeetCode stats: {e}")
        return None

def calculate_streak(username):
    """
    Calculate current and longest streak from LeetCode submissions
    Note: This is a simplified version. For accurate streaks, you'd need
    to fetch submission history which requires authentication.
    """
    # This is a placeholder - actual streak calculation would require
    # accessing submission calendar data which needs authentication
    return 0, 0

def update_readme(stats, username):
    """Update README.md with fetched stats"""
    
    if not stats or 'data' not in stats:
        print("No valid stats data received")
        return False
    
    matched_user = stats['data'].get('matchedUser')
    if not matched_user:
        print("User not found")
        return False
    
    # Extract total problems solved
    ac_submissions = matched_user['submitStats']['acSubmissionNum']
    total_solved = next((item['count'] for item in ac_submissions if item['difficulty'] == 'All'), 0)
    
    # Get current streak (simplified - would need auth for accurate data)
    current_streak, longest_streak = calculate_streak(username)
    
    # Read current README
    with open('README.md', 'r') as f:
        content = f.read()
    
    # Find and extract the started date if it exists
    started_match = re.search(r'\| 📅 Started On \| (.*?) \|', content)
    if started_match and started_match.group(1).strip() != '—':
        started_date = started_match.group(1).strip()
    else:
        started_date = datetime.now().strftime('%B %d, %Y')
    
    # Update the stats table
    stats_pattern = r'\| Metric \| Count \|[\s\S]*?\| 📅 Started On \| .*? \|'
    
    new_stats = f"""| Metric | Count |
|---|---|
| ✅ Problems Solved | {total_solved} |
| 🔥 Current Streak | {current_streak} days |
| 🏆 Longest Streak | {longest_streak} days |
| 📅 Started On | {started_date} |"""
    
    updated_content = re.sub(stats_pattern, new_stats, content)
    
    # Write updated README
    with open('README.md', 'w') as f:
        f.write(updated_content)
    
    print(f"✅ Updated stats: {total_solved} problems solved")
    return True

if __name__ == "__main__":
    username = os.getenv('LEETCODE_USERNAME')
    
    if not username:
        print("❌ LEETCODE_USERNAME environment variable not set")
        print("Add your LeetCode username to GitHub Secrets")
        exit(1)
    
    print(f"Fetching stats for user: {username}")
    stats = get_leetcode_stats(username)
    
    if stats:
        success = update_readme(stats, username)
        if success:
            print("🎉 README updated successfully!")
        else:
            print("❌ Failed to update README")
            exit(1)
    else:
        print("❌ Failed to fetch stats")
        exit(1)
